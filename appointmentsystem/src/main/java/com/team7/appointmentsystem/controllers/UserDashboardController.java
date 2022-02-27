package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.UserDashboard;
import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.repository.AppointmentRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class UserDashboardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CommentsRepository commentsRepository;


    List<Map<String,Object>> totalAppointments;
    List<Map<String,Object>> upcomingAppointments;
    List<Map<String,Object>> totalReviews;
    List<Map<String,Object>> favourites;
    @GetMapping("/login/{userid}/userDashboard")
    public UserDashboard userDashboard(@PathVariable("userid") Long userid){
        Users user= userRepository.findById(userid).get();

        //String userName = user.getFirstname()+user.getLastname();
        //System.out.println("USERNAME"+userName);
        UserDashboard userDashboard=new UserDashboard();
        //userDashboard.userId=userid;
        userDashboard.firstName=user.getFirstName();
        userDashboard.lastname=user.getLastName();




        // getting user details
        LocalDateTime now = LocalDateTime.now();
        totalAppointments=appointmentRepository.TotalAppointmentByUserid(userid);
        upcomingAppointments=appointmentRepository.getUpcomingAppointments(userid,now);
        totalReviews=commentsRepository.getTotalReviews(userid);
        favourites=commentsRepository.getFavourites(userid);

        userDashboard.totalAppointments=totalAppointments.size();
        userDashboard.upcomingAppointments=upcomingAppointments.size();
        userDashboard.totalReviews = totalReviews.size();
        userDashboard.favourites = favourites.size();


        //Pageable pageable = (Pageable) PageRequest.of(0,3);
        //user.saloonServices = serviceRepository.getByServiceNameOrderByServicePriceAsc("Saloon");
        //user.doctorServices = serviceRepository.getByServiceNameOrderByServicePriceAsc("Doctor");
        //user.restaurantServices  =serviceRepository.getByServiceNameOrderByServicePriceAsc("Restaurant");
        userDashboard.services=servicesRepository.getByServiceNameOrderByServicePriceAsc();

        //System.out.println("USER"+user);
        //model.addAttribute("user",user);
        return userDashboard;
    }
    @GetMapping("/login/{userid}/userDashboard/appointments")
    public List<Map<String,Object>> appointments(){
        return totalAppointments;
    }
    @GetMapping("/login/{userid}/userDashboard/upcoming_appointments")
    public List<Map<String,Object>> upcoming_appointments(){
        return upcomingAppointments;
    }
    @GetMapping("/login/{userid}/userDashboard/comments")
    public List<Map<String,Object>> comments(){
        return totalReviews;
    }
    @GetMapping("/login/{userid}/userDashboard/favourites")
    public List<Map<String,Object>> favourites(){
        return favourites;
    }
}
