package com.team7.appointmentsystem.entity;

import java.util.List;
import java.util.Map;

public class UserDashboard {


    public String firstName;
    public String lastname;
    public int totalAppointments;
    public int upcomingAppointments;
    public int totalReviews;
    public int favourites;
    public List<Map<String,Object>> services;

    public UserDashboard() {
        this.firstName = firstName;
        this.lastname = lastname;
        this.totalAppointments = totalAppointments;
        this.upcomingAppointments = upcomingAppointments;
        this.totalReviews = totalReviews;
        this.favourites = favourites;
        this.services = services;
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

    public List<Map<String, Object>> getServices() {
        return services;
    }

    public void setServices(List<Map<String, Object>> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "UserDashboard{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalAppointments=" + totalAppointments +
                ", upcomingAppointments=" + upcomingAppointments +
                ", totalReviews=" + totalReviews +
                ", favourites=" + favourites +
                ", services=" + services +
                '}';
    }
}
