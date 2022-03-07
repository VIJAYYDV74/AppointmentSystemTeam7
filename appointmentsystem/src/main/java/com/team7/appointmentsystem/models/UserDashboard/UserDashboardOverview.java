package com.team7.appointmentsystem.models.UserDashboard;

import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.models.BusinessDetails.OtherBusinesses;

import java.util.List;
import java.util.Map;

public class UserDashboardOverview {


    public String firstName;
    public String lastname;
    public int totalAppointments;
    public int upcomingAppointments;
    public int totalReviews;
    public int favourites;
    public List<OtherBusinesses> Salooons,Hotels,Hospitals;

    public UserDashboardOverview() {
    }

    public UserDashboardOverview(String firstName, String lastname, int totalAppointments, int upcomingAppointments, int totalReviews, int favourites, List<OtherBusinesses> salooons, List<OtherBusinesses> hotels, List<OtherBusinesses> hospitals) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.totalAppointments = totalAppointments;
        this.upcomingAppointments = upcomingAppointments;
        this.totalReviews = totalReviews;
        this.favourites = favourites;
        Salooons = salooons;
        Hotels = hotels;
        Hospitals = hospitals;
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

    public List<OtherBusinesses> getSalooons() {
        return Salooons;
    }

    public void setSalooons(List<OtherBusinesses> salooons) {
        Salooons = salooons;
    }

    public List<OtherBusinesses> getHotels() {
        return Hotels;
    }

    public void setHotels(List<OtherBusinesses> hotels) {
        Hotels = hotels;
    }

    public List<OtherBusinesses> getHospitals() {
        return Hospitals;
    }

    public void setHospitals(List<OtherBusinesses> hospitals) {
        Hospitals = hospitals;
    }
}
