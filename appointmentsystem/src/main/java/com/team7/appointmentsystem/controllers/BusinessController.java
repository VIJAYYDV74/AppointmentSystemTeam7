package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.entity.Visiting;
import com.team7.appointmentsystem.exceptions.UserNotFoundException;
import com.team7.appointmentsystem.miscellinious.BusinessDetails;
import com.team7.appointmentsystem.resultapis.HomepageAPI1;
import com.team7.appointmentsystem.services.BusinessService;
import com.team7.appointmentsystem.services.CommentsService;
import com.team7.appointmentsystem.services.RegisterService;
import com.team7.appointmentsystem.services.VisitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private VisitingService visitingService;

    @Autowired
    private RegisterService registerService;

//    @GetMapping("/homepage")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    public ModelAndView viewHomePage(){
//        ModelAndView modelAndView = new ModelAndView("index.html");
//        List<HomepageAPI1> api1 = businessService.getBusinessesCount();
//        List<List<BusinessDetails>> api2 = businessService.getTop3BusinessesInEachCategory();
//        List<Comments> api3 = commentsService.getLatestComments();
//        List<Business> api4 = businessService.getJoins();
//        //System.out.println(api4.get(0));
//        modelAndView.addObject("api1", api1);
//        modelAndView.addObject("api2", api2);
//        modelAndView.addObject("api3", api3);
//        return modelAndView;
//    }

    @GetMapping("/homepage/api1")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<HomepageAPI1>> getAPI1(){
        List<HomepageAPI1> api1 = businessService.getBusinessesCount();
        return ResponseEntity.ok(api1);
    }


    @GetMapping("/homepage/api2")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<List<BusinessDetails>>> getAPI2(){
        List<List<BusinessDetails>> api2 = businessService.getTop3BusinessesInEachCategory();
        return ResponseEntity.ok(api2);
    }

    @GetMapping("/homepage/api3")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Comments> getAPI3(){
        List<Comments> api3 = commentsService.getLatestComments();
        return api3;
    }

    //get the userid value from the session variable (if logged in only this function gets called)
    @GetMapping("/getBusinessesOfUser/{userid}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> getBusinesses(@PathVariable long userid) throws UserNotFoundException {
        return ResponseEntity.ok(businessService.getUserBusinesses(userid));
    }

    @GetMapping("/getBusinessesByCategory/{categoryName}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Business>> getBusinessesByCategoryName(@PathVariable String categoryName){
        return ResponseEntity.ok(businessService.getBusinessesByCategoryName(categoryName));
    }

    @GetMapping("/getAllBusinesses")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Business>> getAllBusinesses(){
        return ResponseEntity.ok(businessService.getBusinesses());
    }

    @Transactional
    @PostMapping("/registerBusiness/{userid}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> registerBusiness(@PathVariable Long userid, @RequestBody Business business){
        return ResponseEntity.ok(businessService.registerBusiness(userid, business));
    }

    @GetMapping("/user/getBusiness/{businessId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Business> getBusiness(@PathVariable long businessId){
        Business business = businessService.getBusiness(businessId);
        Users users = null;
        if (users==null){
            Visiting visiting = new Visiting(business);
            visitingService.businessVisited(visiting);
        }
        else{
            Visiting visiting = new Visiting(users, business);
            visitingService.businessVisited(new Visiting(users, business));
        }
        return ResponseEntity.ok(business);
    }

    @GetMapping("/user/getBusinessByBusinessName/{businessId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Business> getBusinessById(@PathVariable long businessId){
        return ResponseEntity.ok(businessService.getBusiness(businessId));
    }

}