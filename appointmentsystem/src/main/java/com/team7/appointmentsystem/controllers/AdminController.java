package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<Comments> comments= commentsRepository.topBusiness();
            List<Map<String, Object>> topBusiness=new ArrayList<>();
        for(Comments b:comments){
            Map<String,Object> business=new HashMap<>();
            business.put("businessname",b.getBusiness().getBusinessName());
            List<Services> services=b.getBusiness().getServices();
            Services service=new Services();
            int price=0;
            for(Services s:services){
                if(s.getServicePrice()>price){
                    service=s;
                }
            }
            business.put("servicename",service.getServiceName());
            business.put("serviceprice",service.getServicePrice());
            business.put("category",b.getBusiness().getCategories().getCategoryName());
            topBusiness.add(business);

        }
        admin.setTopBusinesses(topBusiness);
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
        //List<Map<String,Object>> businesses=new ArrayList<>();

        List<Map<String,Object>> allBusinesses=new ArrayList<>();
        List<Business> businesses=businessRepository.getAllbusinesses();
        for(Business business:businesses){
            Map<String,Object> businessDetails=new HashMap<>();
            Long id = business.getBusinessid();
            businessDetails.put("businessname",business.getBusinessName());
            businessDetails.put("email",business.getBusinessEmail());
            businessDetails.put("businessId",business.getBusinessid());
            
            businessDetails.put("category",business.getCategories().getCategoryName());
            List<Services> services= servicesRepository.findByBusinessBusinessid(id);
            Services service=new Services();
            int p=0;
            List<Appointment> appointments=appointmentRepository.findByBusinessBusinessid(business.getBusinessid());
            for (Appointment a:appointments){
                p+=a.getTotalPrice();
            }
            //businessDetails.put("servicesname",service.getServiceName());
            businessDetails.put("saleValue",p);
            List<Comments> comments=commentsRepository.findByBusinessBusinessid(id);
            int rating=0;
            for(Comments c:comments)
            	rating+=c.getRating();
            businessDetails.put("ratings",rating);
            allBusinesses.add(businessDetails);

        }
        return allBusinesses;
    }
    @GetMapping("/admin_dashboard/bookings")
    public List<Map<String,Object>> adminDashboard_bookings(){
        List<Map<String,Object>> totalBookings=new ArrayList<>();
        List<Appointment> Appointments=appointmentRepository.getAllAppointments();

        for (Appointment appointment:Appointments){
            Map<String,Object> booking =new HashMap<>();
            booking.put("bookeddate",appointment.getBookedDate());
            booking.put("name",appointment.getUsers().getFirstName()+appointment.getUsers().getLastName());
            booking.put("businessname",appointment.getBusiness().getBusinessName());
            booking.put("serviceprice",appointment.getServices().getServicePrice());
            booking.put("servicename",appointment.getServices().getServiceName());
            totalBookings.add(booking);

        }
        return totalBookings;
    }

}
