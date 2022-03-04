package com.team7.appointmentsystem.models;

public class BusinessDashboard {
    public int totalAppointments;
    public int upcomingAppointments;
    public int totalReviews;
    public int totalEarnings;
    public int todaysEarning;
    public int yesterdaysEarning;
    public int last7daysEarning;
    public int last30daysEarning;

    public BusinessDashboard() {
    }

    public BusinessDashboard(int totalAppointments, int upcomingAppointments, int totalReviews, int totalEarnings, int todaysEarning, int yesterdaysEarning, int last7daysEarning, int last30daysEarning) {
        this.totalAppointments = totalAppointments;
        this.upcomingAppointments = upcomingAppointments;
        this.totalReviews = totalReviews;
        this.totalEarnings = totalEarnings;
        this.todaysEarning = todaysEarning;
        this.yesterdaysEarning = yesterdaysEarning;
        this.last7daysEarning = last7daysEarning;
        this.last30daysEarning = last30daysEarning;
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

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public int getTodaysEarning() {
        return todaysEarning;
    }

    public void setTodaysEarning(int todaysEarning) {
        this.todaysEarning = todaysEarning;
    }

    public int getYesterdaysEarning() {
        return yesterdaysEarning;
    }

    public void setYesterdaysEarning(int yesterdaysEarning) {
        this.yesterdaysEarning = yesterdaysEarning;
    }

    public int getLast7daysEarning() {
        return last7daysEarning;
    }

    public void setLast7daysEarning(int last7daysEarning) {
        this.last7daysEarning = last7daysEarning;
    }

    public int getLast30daysEarning() {
        return last30daysEarning;
    }

    public void setLast30daysEarning(int last30daysEarning) {
        this.last30daysEarning = last30daysEarning;
    }
}
