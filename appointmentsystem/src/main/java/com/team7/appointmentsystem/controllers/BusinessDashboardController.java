package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import com.team7.appointmentsystem.services.BusinessDashboardServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


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
    public String registerBusiness(@PathVariable long userid, @RequestBody Business business){

        return businessDashboardServices.registerBusiness(userid,business);

    }

    @PostMapping("{businessid}/services")
    public String addServices(@PathVariable long businessid, @RequestBody Services service){

        return businessDashboardServices.addServices(businessid,service);
    }

    @PutMapping("{businessid}/services/{serviceid}")
    public String updateServices(@PathVariable long businessid,@PathVariable long serviceid,@RequestBody Services updateService){

        return  businessDashboardServices.updateServices(businessid,serviceid,updateService);
    }

    @DeleteMapping ("{businessid}/services/{serviceid}")
    public String deleteServices(@PathVariable long businessid,@PathVariable long serviceid){

        return businessDashboardServices.deleteServices(businessid,serviceid);
    }
    @GetMapping("getB")
    public List<Business> getB(){
        return businessRepository.findAll();
    }


    @PostMapping("/profile/uploadPhoto/{businessid}")
    public ResponseEntity<String> saveProfile(@RequestParam("profileImg") MultipartFile multipartFile, @PathVariable long businessid) throws IOException{
        return businessDashboardServices.saveProfile(multipartFile,businessid);
    }


    @GetMapping("getS/{businessid}")
    public List<Services> getS(@PathVariable long businessid){
        return servicesRepository.findByBusinessBusinessid(businessid);
    }

}
