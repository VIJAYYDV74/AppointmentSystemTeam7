package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.models.AdminDashboardModels.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AdminDashboardServices {
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

    public Admin adminDashboard(){

        try {
            LocalDateTime now = LocalDateTime.now();

            Admin admin = new Admin();

            List<AllBusineesses> topBusinesse= adminDashboard_businesses();

            class SortById implements Comparator<AllBusineesses> {
                // Used for sorting in ascending order of ID
                public int compare(AllBusineesses a, AllBusineesses b)
                {
                    return b.saleValue - a.saleValue;
                }
            }
            Collections.sort(topBusinesse, new SortById());
            admin.topBusinesses=topBusinesse;

            admin.totalUsers = userRepository.countTotalUser();
            admin.newUsersThisWeek = userRepository.countTotalUserByThisWeek(now.minusDays(8), now);
            admin.totalBusinesses = businessRepository.countTotalBusiness();
            //admin.comments= commentsRepository.findAllratings();
            admin.newBusinessesToday = businessRepository.countBusinessesToday(now);
            admin.totalRevenue = paymentsRepository.countTotalRevenue();
            admin.revenueThisWeek = paymentsRepository.countRevenueThisWeek(now.minusDays(8), now);
            admin.totalBookings=appointmentRepository.getAllAppointments().size();
            admin.bookingsToday=appointmentRepository.countBookingsToday(now);
            return admin;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
            return null;
        }

    }


    public List<AllUsers> adminDashboard_users(){
        List<AllUsers> allUsers=new ArrayList<>();

        List<Users> users=userRepository.findAll();
        for(Users u:users){
            AllUsers aUser=new AllUsers();
            aUser.userId=u.getUserid();
            aUser.userName=u.getFirstName()+u.getLastName();
            aUser.userEmail=u.getEmail();
            aUser.createdDate= String.valueOf(u.getCreatedTime());
            allUsers.add(aUser);
        }
        return allUsers;
    }

    public List<AllBusineesses> adminDashboard_businesses(){

        List<AllBusineesses> allBusineesses=new ArrayList<>();

        List<Business> businesses=businessRepository.findAll();
        for(Business b:businesses){
            AllBusineesses a=new AllBusineesses();
            a.businessId=b.getBusinessid();
            a.businessName=b.getBusinessName();
            a.businessEmail=b.getBusinessEmail();
            List<Appointment> appointments=appointmentRepository.findByBusinessBusinessid(b.getBusinessid());
            int SaleValue=0;
            int ratings=0;
            int count=0;
            for(Appointment ap:appointments){
                SaleValue+=ap.getTotalPrice();

            }


            a.categoryName=b.getCategories().getCategoryName();
            a.saleValue=SaleValue;
            List<Comments> comments=commentsRepository.findByBusinessBusinessid(b.getBusinessid());
            for(Comments c:comments){
                ratings+=c.getRating();
                count++;
            }
            if(count!=0) {
                ratings /= count;
            }
            a.rating=ratings;

            allBusineesses.add(a);

        }
        return  allBusineesses;


    }

    public List<AllBooking> adminDashboard_bookings(){

        List<AllBooking> allBookings = new ArrayList<>();
        List<Appointment> appointments= appointmentRepository.getAllAppointments();
        for(Appointment a: appointments){
            AllBooking Booking=new AllBooking();
            Booking.userName=a.getUsers().getFirstName()+a.getUsers().getLastName();
            Booking.businessName=a.getBusiness().getBusinessName();
            Booking.serviceName=a.getServices().getServiceName();
            Booking.price=a.getTotalPrice();
            Booking.bookedDate= String.valueOf(a.getBookedDate());
            allBookings.add(Booking);
        }
        return allBookings;




    }
}
