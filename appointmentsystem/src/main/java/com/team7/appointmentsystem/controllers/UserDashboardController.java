package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.models.UserDashboard.Reviews;
import com.team7.appointmentsystem.models.UserDashboard.TotalAppointments;
import com.team7.appointmentsystem.models.UserDashboard.UserDashboardOverview;

import com.team7.appointmentsystem.services.UserDashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserDashboardController {



    @Autowired
    UserDashboardServices userDashboardServices;



    @GetMapping("/login/{userid}/userDashboard")
    public UserDashboardOverview userDashboard(@PathVariable("userid") Long userid){
        UserDashboardOverview userDashboard= userDashboardServices.userDashboard(userid);

        return userDashboard;
    }


    @GetMapping("/login/{userid}/userDashboard/appointments")
    public List<TotalAppointments> appointments(){

        return userDashboardServices.getAllappointments();
    }


    @GetMapping("/login/{userid}/userDashboard/upcoming_appointments")
    public List<TotalAppointments> upcoming_appointments(){
        return userDashboardServices.getUpcomingAppointments();
    }


    @GetMapping("/login/{userid}/userDashboard/comments")
    public List<Reviews> comments(){
        return userDashboardServices.getAllreviews();
    }


    @GetMapping("/login/{userid}/userDashboard/favourites")
    public List<Reviews> favourites(){
        return userDashboardServices.getFavourites();
    }
}
