package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BusinessDetailsController {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    CommentsRepository commentsRepository;

  
    @GetMapping("{businessid}/business_overview")
    public Business businessOverview(@PathVariable Long businessid){
        /*List<Map<String,Object>> business =businessRepository.getBusiness(businessid);
        List<Map<String,Object>> category=commentsRepository.getReviews(businessid);
        List<Map<String,Object>> service=servicesRepository.getPricing(businessid);

        business.add("category",category);*/
        try {
            Optional<Business> business = businessRepository.findById(businessid);

            return business.get();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("{businessid}/gallery")
    public String gallery(@PathVariable Long businessid){
        String images=businessRepository.getImages(businessid);
        return images;
    }

    @GetMapping("{businessid}/pricing")
    public List<Map<String,Object>> pricing(@PathVariable Long businessid){
        List<Map<String,Object>> services=new ArrayList<>();
        services=servicesRepository.getPricing(businessid);
        return services;
    }

    @GetMapping("{businessid}/reviews")
    public List<Map<String,Object>> reviews(@PathVariable Long businessid){
        List<Map<String,Object>> reviews = new ArrayList<>();

        int totalRating = 0;
        int count=0;
        List<Comments> comments=commentsRepository.findByBusinessBusinessid(businessid);
        for(Comments c:comments){
            Map<String,Object> m=new HashMap<>();
            m.put("commentedby",c.getUsers().getFirstName()+c.getUsers().getLastName());
            m.put("ratings",c.getRating());
            m.put("feedback",c.getFeedback());
            totalRating+=c.getRating();
            count++;
            reviews.add(m);
        }
        if (count!=0)
        totalRating=totalRating/count;
        Map<String,Object> m=new HashMap<>();
        m.put("avgRatings",totalRating);
        reviews.add(m);

        return reviews;
    }
}
