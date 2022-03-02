package com.team7.appointmentsystem.models.UserDashboard;

import com.team7.appointmentsystem.entity.Services;

import java.util.List;
import java.util.Map;

public class UserDashboardOverview {


    public String firstName;
    public String lastname;
    public int totalAppointments;
    public int upcomingAppointments;
    public int totalReviews;
    public int favourites;
    public List<Services> saloonServices,restaurantServices,doctorService;

    public UserDashboardOverview() {
    }

    public UserDashboardOverview(String firstName, String lastname, int totalAppointments, int upcomingAppointments, int totalReviews, int favourites, List<Services> saloonServices, List<Services> restaurantServices, List<Services> doctorService) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.totalAppointments = totalAppointments;
        this.upcomingAppointments = upcomingAppointments;
        this.totalReviews = totalReviews;
        this.favourites = favourites;
        this.saloonServices = saloonServices;
        this.restaurantServices = restaurantServices;
        this.doctorService = doctorService;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setUpcomingAppointments(int upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public int getFavourites() {
        return favourites;
    }

    public void setFavourites(int favourites) {
        this.favourites = favourites;
    }

    public List<Services> getSaloonServices() {
        return saloonServices;
    }

    public void setSaloonServices(List<Services> saloonServices) {
        this.saloonServices = saloonServices;
    }

    public List<Services> getRestaurantServices() {
        return restaurantServices;
    }

    public void setRestaurantServices(List<Services> restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    public List<Services> getDoctorService() {
        return doctorService;
    }

    public void setDoctorService(List<Services> doctorService) {
        this.doctorService = doctorService;
    }
}
