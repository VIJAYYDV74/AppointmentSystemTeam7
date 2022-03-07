package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.models.BusinessDashboard;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class BusinessDashboardServices {


    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    BusinessAddressRepository businessAddressRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    BusinessWorkingHoursRepository businessWorkingHoursRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    CommentsRepository commentsRepository;


    List<Appointment> TotalAppointments;
    List<Appointment> UpcomingAppointments;
    List<Comments> TotalReviews;

    public BusinessDashboard business_Dashboard(long businessid){

        try {
            TotalAppointments = appointmentRepository.findByBusinessBusinessid(businessid);
            LocalDateTime now = LocalDateTime.now();
            UpcomingAppointments = appointmentRepository.getUpcomingBusinessAppointments(businessid, now);
            TotalReviews = commentsRepository.findByBusinessBusinessid(businessid);

            BusinessDashboard businessDashboard = new BusinessDashboard();

            businessDashboard.totalAppointments = TotalAppointments.size();
            businessDashboard.upcomingAppointments = UpcomingAppointments.size();
            businessDashboard.totalReviews = TotalReviews.size();

            int temp = 0;
            for (Appointment a : TotalAppointments) {
                if(a.getPayments()!=null) {
                    temp += a.getPayments().getAmount();
                }
                else{
                    temp=-1;
                }
            }
            businessDashboard.totalEarnings = temp;
            temp = 0;
            for (Appointment a : TotalAppointments) {
                if(a.getPayments()!=null) {
                    Instant instant1 = a.getPayments().getPaymentDate().toInstant(ZoneOffset.UTC);
                    Date date1 = Date.from(instant1);


                    Instant instant2 = now.toInstant(ZoneOffset.UTC);
                    Date date2 = Date.from(instant2);

                    if (date1.equals(date2)) {
                        temp += a.getPayments().getAmount();
                    }
                }
                else{
                    temp=-1;
                }
            }
            businessDashboard.todaysEarning = temp;

            temp = 0;
            for (Appointment a : TotalAppointments) {
                if(a.getPayments()!=null) {
                    Instant instant1 = a.getPayments().getPaymentDate().toInstant(ZoneOffset.UTC);
                    Date date1 = Date.from(instant1);

                    Instant instant2 = now.minusDays(1).toInstant(ZoneOffset.UTC);
                    Date date2 = Date.from(instant2);

                    if (date1.equals(date2)) {
                        temp += a.getPayments().getAmount();
                    }
                }
                else{
                    temp=-1;
                }
            }
            businessDashboard.yesterdaysEarning = temp;

            temp = 0;
            LocalDateTime now1 = now.minusDays(7);
            for (Appointment a : TotalAppointments) {
                if(a.getPayments()!=null) {
                    Instant instant1 = a.getPayments().getPaymentDate().toInstant(ZoneOffset.UTC);
                    Date date1 = Date.from(instant1);

                    Instant instant2 = now1.toInstant(ZoneOffset.UTC);
                    Date date2 = Date.from(instant2);


                    Instant instant3 = now.toInstant(ZoneOffset.UTC);
                    Date date3 = Date.from(instant3);

                    if (date1.after(date2) && date1.before(date3)) {
                        temp += a.getPayments().getAmount();
                    }
                }
                else{
                    temp=-1;
                }
            }
            businessDashboard.last7daysEarning = temp;


            temp = 0;
            now1 = now.minusDays(30);
            for (Appointment a : TotalAppointments) {
                if(a.getPayments()!=null) {
                    Instant instant1 = a.getPayments().getPaymentDate().toInstant(ZoneOffset.UTC);
                    Date date1 = Date.from(instant1);

                    Instant instant2 = now1.toInstant(ZoneOffset.UTC);
                    Date date2 = Date.from(instant2);


                    Instant instant3 = now.toInstant(ZoneOffset.UTC);
                    Date date3 = Date.from(instant3);

                    if (date1.after(date2) && date1.before(date3)) {
                        temp += a.getPayments().getAmount();
                    }
                }
                else{
                    temp=-1;
                }
            }
            businessDashboard.last30daysEarning = temp;

            return businessDashboard;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String registerBusiness(long userid, Business business){

        try {
                Users users = userRepository.findById(userid).get();
                int id = business.getCategories().getCategoryId();
                Categories categories = categoriesRepository.findById(id).get();


                business.setBusinessEmail(users.getEmail());
                business.setBusinessMobileNumber(users.getMobileNumber());
                business.setBusinessTitle(business.getBusinessName());
                business.setUsers(users);
                business.setCreatedTime(LocalDateTime.now());
                business.setBusinessAddress(business.getBusinessAddress());
                business.setCategories(categories);
                businessAddressRepository.save(business.getBusinessAddress());
                businessRepository.save(business);
                List<BusinessWorkingHours> l1 = business.getWorkingHours();
                for (BusinessWorkingHours businessWorkingHours : l1) {
                    businessWorkingHours.setBusinessHours(business);
                    businessWorkingHoursRepository.save(businessWorkingHours);
                }
                return "business registered";

        }
        catch (Exception e){
            return "Business Not registered";
        }

    }

    public ResponseEntity<String> saveProfile( MultipartFile multipartFile, long businessId) throws IOException
    {
        String profileImg = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Business business = businessRepository.findByBusinessId(businessId);
        business.setBusinessImages(profileImg);
        Business savedBusiness = businessRepository.save(business);
        String uploadDir = "./profile-image/" + savedBusiness.getBusinessid();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(profileImg);
            System.out.println(filePath.toFile().getAbsolutePath().toString()) ;
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            throw  new IOException("Could not save uploaded file:" + profileImg);
        }
        return ResponseEntity.ok("File uploaded Successfully");
    }




    public String addServices(long businessid, Services service){

        try {
            Business business= businessRepository.findById(businessid).get();
            service.setBusiness(business);

            Services services=servicesRepository.save(service);
            // System.out.println(services);
            return "Service Added";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unable to add service ";
        }

    }


    public String updateServices(long businessid,long serviceid,Services updateService){

        try {
            Services services = servicesRepository.findById(serviceid).get();
            //System.out.println(services);
            services.setServiceName(updateService.getServiceName());
            services.setServiceDesc(updateService.getServiceDesc());
            services.setServicePrice(updateService.getServicePrice());
            servicesRepository.save(services);
            //System.out.println(services);
            return "Service updated";
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be updated";
        }


    }


    public String deleteServices( long businessid, long serviceid){

        try {
            Services services=servicesRepository.findByServiceidAndBusinessBusinessid(serviceid,businessid);
            servicesRepository.delete(services);
            return "Service deleted";
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be deleted";
        }

    }

}
