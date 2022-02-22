package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.*;
import com.team7.appointmentsystem.miscellinious.BusinessDetails;
import com.team7.appointmentsystem.repository.*;
import com.team7.appointmentsystem.resultapis.HomepageAPI1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class BusinessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private BusinessWorkingHoursRepository businessWorkingHoursRepository;

    @Autowired
    private BusinessAddressRepository businessAddressRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private BusinessNotificationsService businessNotificationsService;

    public static final Logger logger = LoggerFactory.getLogger(BusinessService.class);

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public BusinessService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Business> getBusinesses(){
        List<Business> businesses = new ArrayList<>();
        businessRepository.findAll().forEach(businesses::add);
        return businesses;
    }

    public Object getUserBusinesses(long userid) throws UserNotFoundException {
        try{
            Users users = userRepository.findById(userid).orElse(null);
            if(users==null) {
                throw new UserNotFoundException("User not found");
            }
            List<Business> businesses =  businessRepository.findByUsersUserid(userid);
            if(businesses.size()==0){
                throw new BusinessNotFoundException("Businesses not found");
            }
            else{
                return businesses;
            }
        }catch (UserNotFoundException e){
            return e.getMessage();
        } catch (BusinessNotFoundException e) {
            return e.getMessage();
        }
    }

    public String registerBusiness(Long userid, Business business){
        // Get the user details
        try{
            Business business1 = businessRepository.
                    findByBusinessEmail(business.getBusinessEmail());
            Users users = userRepository.getById(userid);
            int id = business.getCategories().getCategoryId();
            Categories categories = categoriesRepository.findById(id).orElse(null);
            if (categories==null){
                throw new CategoryNotFoundException("CategoryNotFoundException");
            }
            business.setUsers(users);
            business.setCreatedTime(LocalDateTime.now());
            business.setBusinessAddress(business.getBusinessAddress());
            business.setCategories(categories);
            BusinessAddress businessAddress = businessAddressRepository.
                    save(business.getBusinessAddress());
            if (businessAddress==null){
                throw new InternalServerException("InternalServerException");
            }
            Business business2 = businessRepository.save(business);
            if (business2==null){
                throw new InternalServerException("InternalServerException");
            }
            List<BusinessWorkingHours> l1 = business.getWorkingHours();
            if (l1.size()<7 || l1==null){
                throw new WorkingHoursException("WorkingHoursException");
            }
            for (BusinessWorkingHours businessWorkingHours: l1) {
                businessWorkingHours.setBusinessHours(business);
                BusinessWorkingHours businessWorkingHours1 = businessWorkingHoursRepository.
                        save(businessWorkingHours);
                if (businessWorkingHours1==null){
                    throw new InternalServerException("InternalServerException");
                }
            }
            List<Services> services = business.getServices();
            if (services==null){
                throw new ServiceNotFoundException("ServicesNotFound");
            }
            for(Services services1: services){
                services1.setBusiness(business);
                Services services2 = servicesRepository.save(services1);
                if (services2==null){
                    throw new InternalServerException("InternalServerException");
                }
            }
            return "Business Registered";
        }
        catch (ServiceNotFoundException serviceNotFoundException){
            logger.error(serviceNotFoundException.getMessage());
            return "Business Registered";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "Business not registered";
        }
    }

    public List<HomepageAPI1> getBusinessesCount() {
        try {
            List<HomepageAPI1> res = new ArrayList<>();
            List<Categories> categories = categoriesRepository.findAll();
            if (categories == null || categories.size() == 0) {
                throw new CategoryNotFoundException("CategoriesNotFOundException");
            }
            for (Categories category : categories) {
                HomepageAPI1 test = new HomepageAPI1(category.getCategoryName(),
                        businessRepository.
                        countTotalBusinessByCategoriesCategoryName(category.getCategoryName()));
                res.add(test);
            }
            return res;
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Business> getBusinessesByCategoryName(String categoryName) {
        return businessRepository.findByCategoriesCategoryName(categoryName);
    }

    public List<List<BusinessDetails>> getTop3BusinessesInEachCategory(){
        List<List<BusinessDetails>> listOfBusinesses = new ArrayList<>();
        List<Categories> categories = categoriesRepository.findAll();
        for(Categories categories1:categories){
            listOfBusinesses.add(businessRepository.
                    findTop3ByCategoriesCategoryNameOrderByCreatedTimeDesc(
                            categories1.getCategoryName()
                    ));
        }
        return listOfBusinesses;
    }

    public List<Business> getJoins() {
        return businessRepository.businessServiceJoin();
    }

    public Business getBusiness(long businessId) {
        Business business = businessRepository.findById(businessId).orElse(null);
        return business;
    }

    public BusinessDetails getBusinessByBusinessId(long businessid){
        BusinessDetails businessDetails = businessRepository.findByBusinessid(businessid);
        if (businessDetails!=null){
            businessNotificationsService.
                    sendBusinessNotificationOnBusinessSearched(businessDetails.getBusinessid(), 1);
        }
        return businessDetails;
    }

}