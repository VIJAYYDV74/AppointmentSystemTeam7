package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BusinessDashboardController {


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


    @PostMapping("{userid}/registerBusiness")
    public String registerBusiness(@PathVariable Long userid, @RequestBody Business business, @RequestParam("businessimage")MultipartFile file){

        try {
            Users users = userRepository.findById(userid).get();
            //System.out.println(users);

            //System.out.println(business.getCategories());
            int id = business.getCategories().getCategoryId();
            //System.out.println(id);

            Categories categories = categoriesRepository.findById(id).get();
            //System.out.println(categories);

            // image uploading and processing
            if(file.isEmpty()){
                System.out.println("File is empty ");
            }
            else {
                business.setBusinessImages(file.getOriginalFilename());
                 File saveFile=new ClassPathResource("static/image").getFile();
                Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
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

    @PostMapping("{businessid}/services")
    public String addServices(@Param("businessid") Long businessid, @RequestBody Services service){

        try {
            Business business= businessRepository.findById(businessid).get();
            service.setBusiness(business);

            Services services=servicesRepository.save(service);
           // System.out.println(services);
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unable to add service ";
        }
        return "Service Added";
    }

    @PutMapping("{businessid}/services")
    public String updateServices(@Param("businessid") Long businessid,@RequestBody Services updateService){

        try {
            Business business = businessRepository.findById(businessid).get();
            updateService.setBusiness(business);
            Services services = servicesRepository.save(updateService);
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be updated";
        }

        return "Service updated";
    }

    @DeleteMapping ("{businessid}/services/{serviceid}")
    public String deleteServices(@Param("businessid") Long businessid,@Param("serviceid") Long serviceid){

        try {
            Services services=servicesRepository.findByServiceidAndBusinessBusinessid(serviceid,businessid);
            servicesRepository.delete(services);
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be deleted";
        }
        return "Service deleted";
    }


}
