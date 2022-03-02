package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class BusinessDetailsServices {


    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    CommentsRepository commentsRepository;
    public Business businessOverview(Long businessid){

        try {
            Optional<Business> business = businessRepository.findById(businessid);

            return business.get();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String gallery(Long businessid){
        String images=businessRepository.getImages(businessid);
        return images;
    }

    public List<Map<String,Object>> reviews(Long businessid){
        try {


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

        return reviews;}
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
