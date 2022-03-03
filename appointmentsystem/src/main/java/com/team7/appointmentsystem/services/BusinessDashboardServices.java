package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
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

    public String registerBusiness(Long userid,  Business business, MultipartFile file){

        try {
            Users users = userRepository.findById(userid).get();
            int id = business.getCategories().getCategoryId();
            Categories categories = categoriesRepository.findById(id).get();
            if(file.isEmpty()){
                System.out.println("File is empty ");
            }
            else {
                business.setBusinessImages(file.getOriginalFilename());
                File saveFile=new ClassPathResource("static/image").getFile();
                Path path= Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File is uploaded");
            }

            business.setUsers(users);
            business.setCreatedTime(LocalDateTime.now());
            business.setBusinessAddress(business.getBusinessAddress());
            business.setCategories(categories);
            businessAddressRepository.save(business.getBusinessAddress());
            businessRepository.save(business);
            List<BusinessWorkingHours> l1 = business.getWorkingHours();
            for (BusinessWorkingHours businessWorkingHours: l1) {
                businessWorkingHours.setBusinessHours(business);
                businessWorkingHoursRepository.save(businessWorkingHours);
            }
        }
        catch (Exception e){
            return "Business Not registered";
        }
        return "business registered";
    }


    public String addServices(Long businessid, Services service){

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


    public String updateServices(Long businessid,Services updateService){

        try {
            Business business = businessRepository.findById(businessid).get();
            updateService.setBusiness(business);
            Services services = servicesRepository.save(updateService);
            return "Service updated";
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be updated";
        }


    }


    public String deleteServices( Long businessid, Long serviceid){

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
