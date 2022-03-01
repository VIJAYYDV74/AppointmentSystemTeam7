package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BusinessDetailsController {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    CommentsRepository commentsRepository;

  
    @GetMapping("{businessid}/business_overview")
    public Map<String,Object> businessOverview(@PathVariable("businessid") Long businessid){
        Map<String,Object> business= new HashMap<>();
        business=businessRepository.getBusiness(businessid);
        return business;

    }
    @GetMapping("{businessid}/gallery")
    public String gallery(@Param("businessid") Long businessid){
        String images=businessRepository.getImages(businessid);
        return images;
    }

    @GetMapping("{businessid}/pricing")
    public List<Map<String,Object>> pricing(@Param("businessid") Long businessid){
        List<Map<String,Object>> services=new ArrayList<>();
        services=servicesRepository.getPricing(businessid);
        return services;
    }

    @GetMapping("{businessid}/reviews")
    public List<Map<String,Object>> reviews(@Param("businessid") Long businessid){
        List<Map<String,Object>> reviews =commentsRepository.getReviews(businessid);
        return reviews;
    }
}
