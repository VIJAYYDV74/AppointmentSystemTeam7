package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.models.BusinessDetails.BusinessDetailsReview;
import com.team7.appointmentsystem.models.BusinessDetails.Reviewer;
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

    public BusinessDetailsReview reviews(long businessid){
        try {


        BusinessDetailsReview reviews = new BusinessDetailsReview();

        int totalRating = 0;
        int count=0;
        List<Comments> comments=commentsRepository.findByBusinessBusinessid(businessid);
        reviews.commentedBy=new ArrayList<>();
        for(Comments c:comments){
            Reviewer r=new Reviewer();

            r.userName=c.getUsers().getFirstName()+c.getUsers().getLastName();
            r.userImage=c.getUsers().getProfileImage();
            r.rating=c.getRating();
            r.feedback=c.getFeedback();
            totalRating+=c.getRating();
            count++;
            reviews.commentedBy.add(r);

        }
        if (count!=0) {
            totalRating = totalRating / count;
        }

        reviews.overallRating=totalRating;

        return reviews;}
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
