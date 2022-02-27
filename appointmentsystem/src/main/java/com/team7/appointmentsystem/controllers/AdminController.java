package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PaymentsRepository paymentsRepository;
    @Autowired
    ServicesRepository servicesRepository;

    @GetMapping("/admin_dashboard")
    public Admin adminDashboard(){

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //LocalDateTime before=now.minusDays(8);
        //System.out.println("Before Formatting: " + now);
        // DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        //String formatDateTime = now.format(format);
        //now = LocalDateTime.parse(now.format(form));
        //Date date= Date.from(now.atZone(ZoneId.systemDefault()).toInstant());



        //Instant instant = now.toInstant(ZoneOffset.UTC);
        // Date dates = Date.from(instant);
        // System.out.println(dates);
        Admin admin= new Admin();
       // admin.users= userRepository.getAllUser();
        admin.topBusinesses = commentsRepository.topBusiness();
        //admin.appointments= appointmentRepository.getAllAppointments();
        //admin.payments= paymentsRepository.getAllPayments();
        admin.totalUsers=userRepository.countTotalUser();
        admin.newUsersThisWeek= userRepository.countTotalUserByThisWeek(now.minusDays(8),now);
        admin.totalBusinesses= businessRepository.countTotalBusiness();
        //admin.comments= commentsRepository.findAllratings();
        admin.newBusinessesToday = businessRepository.countBusinessesToday(now);
        admin.totalRevenue = paymentsRepository.countTotalRevenue();
        admin.revenueThisWeek = paymentsRepository.countRevenueThisWeek(now.minusDays(8),now);
        return admin;
    }
    @GetMapping("/admin_dashboard/users")
    public List<Map<String,Object>> adminDashboard_users(){
        List<Map<String,Object>> users=new ArrayList<>();
        users=userRepository.getAllUser();
        return users;
    }
    @GetMapping("/admin_dashboard/businesses")
    public List<Map<String,Object>> adminDashboard_businesses(){
        List<Map<String,Object>> businesses=new ArrayList<>();

        businesses=businessRepository.getAllbusinesses();
        return businesses;
    }
    @GetMapping("/admin_dashboard/bookings")
    public List<Map<String,Object>> adminDashboard_bookings(){
        List<Map<String,Object>> appointments=new ArrayList<>();
        appointments=appointmentRepository.getAllAppointments();
        return appointments;
    }

}
