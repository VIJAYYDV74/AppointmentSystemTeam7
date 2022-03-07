package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.models.BusinessDetails.OtherBusinesses;
import com.team7.appointmentsystem.models.UserDashboard.Reviews;
import com.team7.appointmentsystem.models.UserDashboard.TotalAppointments;
import com.team7.appointmentsystem.models.UserDashboard.UserDashboardOverview;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserDashboardServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    BusinessRepository businessRepository;

    List<Appointment> appointments;
    List<Appointment> upcomingAppointments;
    List<Comments> totalReviews;
    List<Comments> favourites;

    public UserDashboardOverview userDashboard(Long userid){
        Users user= userRepository.findById(userid).get();


        UserDashboardOverview userDashboard=new UserDashboardOverview();

        userDashboard.firstName=user.getFirstName();
        userDashboard.lastname=user.getLastName();




        // getting user details
        LocalDateTime now = LocalDateTime.now();

        appointments=appointmentRepository.findByUsersUserid(userid);
        userDashboard.totalAppointments=appointments.size();

        upcomingAppointments= appointmentRepository.getUpcomingAppointments(userid,now);
        userDashboard.upcomingAppointments=upcomingAppointments.size();

        totalReviews=commentsRepository.findByUsersUserid(userid);
        userDashboard.totalReviews=totalReviews.size();

        favourites=commentsRepository.getFavourites(userid);
        userDashboard.favourites=favourites.size();

        userDashboard.Salooons=new ArrayList<>();
        userDashboard.Hotels=new ArrayList<>();
        userDashboard.Hospitals=new ArrayList<>();
        List<Business> businesses=businessRepository.findAll();
        for(Business b:businesses) {
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
                        Time nowtime=Time.valueOf(LocalTime.now());

                        if(nowtime.after(bw.getStartHour()) && nowtime.before(bw.getEndHour())){
                            o.status="open now";
                        }
                        else{
                            o.status="closed";
                        }
                    }
                }
            }
            if(b.getCategories().getCategoryId() == 3) {
                userDashboard.Salooons.add(o);
            }
            else if(b.getCategories().getCategoryId() == 2) {
                userDashboard.Hotels.add(o);
            }
            else {
                userDashboard.Hotels.add(o);
            }

        }

        return userDashboard;
    }

    public List<TotalAppointments> getAllappointments(long userid) {
        List<TotalAppointments> totalAppointments=new ArrayList<>();
        try {

            appointments=appointmentRepository.findByUsersUserid(userid);
            for (Appointment a : appointments) {
                TotalAppointments t = new TotalAppointments();
                t.appointedDate = String.valueOf(a.getAppointmentDate());
                t.bookedDate = String.valueOf(a.getBookedDate());
                t.beginTime = String.valueOf(a.getBeginTime());
                t.endTime = String.valueOf(a.getEndTime());
                t.businessName = a.getBusiness().getBusinessName();
                t.serviceName = a.getServices().getServiceName();
                if(a.getPayments()!=null) {
                    t.paymentDate = a.getPayments().getPaymentDate().toString();
                    t.paymentMethod = a.getPayments().getPaymentmethod();
                }
                else{
                    t.paymentDate="not done";
                    t.paymentMethod = "not done";
                }

                t.price = a.getTotalPrice();
                totalAppointments.add(t);
            }
            return totalAppointments;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TotalAppointments> getUpcomingAppointments(long userid) {
        List<TotalAppointments> totalAppointments=new ArrayList<>();
        try {

            LocalDateTime now=LocalDateTime.now();
            upcomingAppointments=appointmentRepository.getUpcomingAppointments(userid,now);
            for (Appointment a : upcomingAppointments) {
                TotalAppointments t = new TotalAppointments();
                t.appointedDate = String.valueOf(a.getAppointmentDate());
                t.bookedDate = String.valueOf(a.getBookedDate());
                t.beginTime = String.valueOf(a.getBeginTime());
                t.endTime = String.valueOf(a.getEndTime());
                t.businessName = a.getBusiness().getBusinessName();
                t.serviceName = a.getServices().getServiceName();
                //t.paymentDate = "";
                //t.paymentMethod = "";
                t.price = a.getTotalPrice();
                totalAppointments.add(t);
            }
            return totalAppointments;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Reviews> getAllreviews(long userid) {
        List<Reviews> reviews=new ArrayList<>();
        try {
            totalReviews=commentsRepository.findByUsersUserid(userid);
            for (Comments c: totalReviews){
                Reviews r=new Reviews();
                r.businessName=c.getBusiness().getBusinessName();
                r.rating=c.getRating();
                r.feedback=c.getFeedback();
                r.Date= String.valueOf(c.getLocalDateTime());
                reviews.add(r);
            }
            return reviews;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Reviews> getFavourites(long userid) {
        List<Reviews> reviews=new ArrayList<>();
        try {
            favourites=commentsRepository.getFavourites(userid);
            for (Comments c: favourites){
                Reviews r=new Reviews();
                r.businessName=c.getBusiness().getBusinessName();
                r.rating=c.getRating();
                r.feedback=c.getFeedback();
                r.Date= String.valueOf(c.getLocalDateTime());
                reviews.add(r);
            }
            return reviews;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
