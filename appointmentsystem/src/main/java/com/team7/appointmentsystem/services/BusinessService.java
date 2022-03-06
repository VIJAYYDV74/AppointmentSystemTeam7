package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.*;
import com.team7.appointmentsystem.miscellinious.BusinessDetails;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.repository.*;
import com.team7.appointmentsystem.resultapis.HomepageAPI1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.team7.appointmentsystem.metamodels.Business_.businessAddress;


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
    private CommentsRepository commentsRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private GenderCategoryRepository genderCategoryRepository;

    @Autowired
    private BusinessImagesRepository businessImagesRepository;

    @Autowired
    private NotificationService notificationService;

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
            GenderCategories genderCategories = business.getGenderCategory();
            GenderCategories genderCategories1 = genderCategoryRepository.getById(genderCategories.getId());
            business.setGenderCategory(genderCategories1);
            business.setUsers(users);
            business.setCreatedTime(LocalDateTime.now());
            business.setBusinessAddress(business.getBusinessAddress());
            business.setCategories(categories);


            Business business2 = businessRepository.save(business);
            if (business2==null){
                throw new InternalServerException("InternalServerException");
            }
            List<BusinessWorkingHours> l1 = business.getWorkingHours();
            if (l1.size()<7 || l1==null){
                throw new WorkingHoursException("WorkingHoursException");
            }
            BusinessAddress businessAddress = businessAddressRepository.
                    save(business.getBusinessAddress());
            if (businessAddress==null){
                throw new InternalServerException("InternalServerException");
            }
            for (BusinessWorkingHours businessWorkingHours: l1) {
                businessWorkingHours.setBusiness(business);
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
            List<BusinessImages> businessImages = business.getBusinessImages();
            if (businessImages==null){
                throw new ServiceNotFoundException("ServicesNotFound");
            }
            for(BusinessImages businessImages1: businessImages){
                businessImages1.setBusiness(business);
                BusinessImages businessImages2 = businessImagesRepository.save(businessImages1);
                if (businessImages2==null){
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
            notificationService.
                    sendNotificationOnBusinessSearched(businessDetails.getBusinessid(), 1);
        }
        return businessDetails;
    }

    public StrObject updateBusinessDetails(Long businessId, Business business) {
        try{
            Business fetchBusiness = businessRepository.getById(businessId);
            if(fetchBusiness == null) {
                throw new BusinessNotFoundException("Business does not exist");
            }else {
                fetchBusiness.setBusinessName( business.getBusinessName() );
                fetchBusiness.setBusinessAddress(business.getBusinessAddress());
                fetchBusiness.setBusinessEmail(business.getBusinessEmail());
                fetchBusiness.setSlotDuration(business.getSlotDuration());
                fetchBusiness.setBusinessDescription(business.getBusinessDescription());
                fetchBusiness.setCancellationAvailable(business.isCancellationAvailable());
                fetchBusiness.setBusinessMobileNumber(business.getBusinessMobileNumber());
                fetchBusiness.setBusinessTitle(business.getBusinessTitle());
                businessRepository.save(fetchBusiness);
                return new StrObject("Updated SuccessFully:");
            }
        }catch (BusinessNotFoundException e) {
            return new StrObject(e.getMessage());
        }
    }

    public StrObject deleteService(Long businessId, Long serviceId) {
        try {
            Services service = servicesRepository.findByServiceidAndBusinessBusinessid(serviceId, businessId);
            if(service == null) {
                throw new ServiceNotFoundException("Could not found the Service");
            }else{
                servicesRepository.delete(service);
                return new StrObject("Service removed: " + service.getServiceName());
            }
        }catch (ServiceNotFoundException e) {
            return new StrObject(e.getMessage());
        }
    }
    public StrObject updateService(Long businessId, Long serviceId, Services updateService) {
        try {
            Services service = servicesRepository.findByServiceidAndBusinessBusinessid(serviceId, businessId);
            if(service == null) {
                throw new ServiceNotFoundException("Could not found the Service");
            }else{
                service.setServiceName(updateService.getServiceName());
                service.setServiceDesc(updateService.getServiceDesc());
                service.setServicePrice(updateService.getServicePrice());
                servicesRepository.save(service);
                return new StrObject("Service updated: "+"{" +
                            service.getServiceName()+"\n"+service.getServiceDesc()+"\n"+
                            service.getServicePrice()+
                        "}");
            }
        }catch (ServiceNotFoundException e) {
            return new StrObject(e.getMessage());
        }
    }

    public List<Comments> getReviews (Long businessId) {
        try{
            List<Comments> reviews = commentsRepository.findByBusinessBusinessid(businessId);
            if(reviews == null) {
                throw new NoOneReviewedException("No one has given review to this business");
            }else{
                return reviews;
            }
        }catch (NoOneReviewedException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}