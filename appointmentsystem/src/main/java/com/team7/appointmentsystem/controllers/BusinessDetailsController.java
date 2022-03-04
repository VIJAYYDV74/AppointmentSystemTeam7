package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.models.BusinessDetails.BusinessDetailsReview;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import com.team7.appointmentsystem.services.BusinessDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BusinessDetailsController {


    @Autowired
    BusinessDetailsServices businessDetailsServices;

    @GetMapping("{businessid}/business_overview")
    public Business businessOverview(@PathVariable long businessid){
        return businessDetailsServices.businessOverview(businessid);
    }
  

    @GetMapping("{businessid}/reviews")
    public BusinessDetailsReview reviews(@PathVariable long businessid){
        return businessDetailsServices.reviews(businessid);
    }
}
