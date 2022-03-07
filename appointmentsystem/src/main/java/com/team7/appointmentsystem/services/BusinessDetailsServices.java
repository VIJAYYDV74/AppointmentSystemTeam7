package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.models.BusinessDetails.BusinessDetailsReview;
import com.team7.appointmentsystem.models.BusinessDetails.OtherBusinesses;
import com.team7.appointmentsystem.models.BusinessDetails.Reviewer;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
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
            Business business = businessRepository.findById(businessid).get();

            return business;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /*public String gallery(Long businessid){
        String images=businessRepository.getImages(businessid);
        return images;
    }*/

    public List<OtherBusinesses> similarBusinesses(long businessid){
        try {
            List<OtherBusinesses> otherBusinesses=new ArrayList<>();
            List<Business> businesses = businessRepository.findAll();
            Business business=businessRepository.findByBusinessId(businessid);
            long cId=business.getCategories().getCategoryId();
            for(Business b:businesses){
                long tempcId=b.getCategories().getCategoryId();
                if(tempcId==cId){
                    OtherBusinesses o=new OtherBusinesses();
                    o.businessName=b.getBusinessName();
                    o.image=b.getBusinessImages();
                    o.address=b.getBusinessAddress().getAddressLine2()+b.getBusinessAddress().getCountry();
                    List<Services> services=servicesRepository.findByBusinessBusinessid(b.getBusinessid());
                    int Ratings=0;
                    int count=0;
                    int lowest=100000;
                    for(Services s:services){
                        if(s.getServicePrice()<lowest){
                            lowest=s.getServicePrice();
                        }
                    }
                    o.price=lowest;
                    List<Comments> comments=commentsRepository.findByBusinessBusinessid(b.getBusinessid());
                    for(Comments c:comments){
                        Ratings+=c.getRating();
                        count++;
                    }
                    if(count!=0){
                        Ratings/=count;
                    }
                    o.ratings=Ratings;
                    List<BusinessWorkingHours> bws=b.getWorkingHours();

                    Calendar calendar = Calendar.getInstance();
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    if(dayOfWeek==1){
                        o.status="closed";
                    }
                    else{
                        dayOfWeek-=1;
                        for(BusinessWorkingHours bw:bws){
                            long bwid=bw.getId();
                            if(dayOfWeek==bwid){
                                Time now=Time.valueOf(LocalTime.now());

                                if(now.after(bw.getStartHour()) && now.before(bw.getEndHour())){
                                    o.status="open now";
                                }
                                else{
                                    o.status="closed";
                                }
                            }
                        }
                    }
                    otherBusinesses.add(o);
                }

            }
            return otherBusinesses;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

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
