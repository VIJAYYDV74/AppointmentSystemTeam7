package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import com.team7.appointmentsystem.services.BusinessDashboardServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



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


    @Autowired
    BusinessDashboardServices businessDashboardServices;
    @PostMapping("{userid}/registerBusiness")
    public String registerBusiness(@PathVariable Long userid, @RequestBody Business business, @RequestParam("file")MultipartFile file){

        return businessDashboardServices.registerBusiness(userid,business,file);

    }

    @PostMapping("{businessid}/services")
    public String addServices(@Param("businessid") Long businessid, @RequestBody Services service){

        return businessDashboardServices.addServices(businessid,service);
    }

    @PutMapping("{businessid}/services")
    public String updateServices(@Param("businessid") Long businessid,@RequestBody Services updateService){

        return  businessDashboardServices.updateServices(businessid,updateService);
    }

    @DeleteMapping ("{businessid}/services/{serviceid}")
    public String deleteServices(@Param("businessid") Long businessid,@Param("serviceid") Long serviceid){

        return businessDashboardServices.deleteServices(businessid,serviceid);
    }
   /* @GetMapping("getB")
    public List<Business> getB(){
        return businessRepository.findAll();
    }


    @RequestMapping("/profile/uploadPhoto/{userId}")
    public ResponseEntity<String> saveProfile(@RequestParam("profileImg") MultipartFile multipartFile,
                                              @PathVariable long userId) throws IOException
    {
        String profileImg = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Users user = userRepository.findByUserid(userId);
        user.setProfileImage(profileImg);
        Users savedUser = userRepository.save(user);
        String uploadDir = "./profile-image/" + savedUser.getUserid();
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
    }*/



}
