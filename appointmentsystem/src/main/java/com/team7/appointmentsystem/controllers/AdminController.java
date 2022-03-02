package com.team7.appointmentsystem.controllers;



import com.team7.appointmentsystem.models.AdminDashboardModels.*;
import com.team7.appointmentsystem.services.AdminDashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class AdminController {

    @Autowired
    AdminDashboardServices adminDashboardServices;

    @GetMapping("/admin_dashboard")
    public Admin adminDashboard() {
        Admin admin= adminDashboardServices.adminDashboard();
        return admin;

    }
    @GetMapping("/admin_dashboard/users")
    public List<AllUsers> adminDashboard_users(){
        List<AllUsers> allUsers= adminDashboardServices.adminDashboard_users();
        return allUsers;
    }
    @GetMapping("/admin_dashboard/businesses")
    public List<AllBusineesses> adminDashboard_businesses(){

        List<AllBusineesses> allBusineesses= adminDashboardServices.adminDashboard_businesses();

        return  allBusineesses;
    }
    @GetMapping("/admin_dashboard/bookings")
    public List<AllBooking> adminDashboard_bookings(){

        List<AllBooking> allBookings = adminDashboardServices.adminDashboard_bookings();

        return allBookings;
    }

}
