package com.team7.appointmentsystem.models.AdminDashboardModels;

import java.util.List;

public class Admin {

    //public List<Users> users;
    public List<TopBusinesses> topBusinesses;
    //public List<Appointment> appointments;
    //public List<Payments> payments;
    //public List<Comments> comments;
    public int totalUsers;
    public int newUsersThisWeek;
    public int totalBusinesses;
    public int newBusinessesToday;
    public int totalRevenue;
    public int revenueThisWeek;


    public Admin(){

    }
    public Admin(List<TopBusinesses> topBusinesses, int totalUsers, int newUsersThisWeek, int totalBusinesses, int newBusinessesToday, int totalRevenue, int revenueThisWeek) {
        this.topBusinesses = topBusinesses;
        this.totalUsers = totalUsers;
        this.newUsersThisWeek = newUsersThisWeek;
        this.totalBusinesses = totalBusinesses;
        this.newBusinessesToday = newBusinessesToday;
        this.totalRevenue = totalRevenue;
        this.revenueThisWeek = revenueThisWeek;
    }

    public List<TopBusinesses> getTopBusinesses() {
        return topBusinesses;
    }

    public void setTopBusinesses(List<TopBusinesses> topBusinesses) {
        this.topBusinesses = topBusinesses;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getNewUsersThisWeek() {
        return newUsersThisWeek;
    }

    public void setNewUsersThisWeek(int newUsersThisWeek) {
        this.newUsersThisWeek = newUsersThisWeek;
    }

    public int getTotalBusinesses() {
        return totalBusinesses;
    }

    public void setTotalBusinesses(int totalBusinesses) {
        this.totalBusinesses = totalBusinesses;
    }

    public int getNewBusinessesToday() {
        return newBusinessesToday;
    }

    public void setNewBusinessesToday(int newBusinessesToday) {
        this.newBusinessesToday = newBusinessesToday;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getRevenueThisWeek() {
        return revenueThisWeek;
    }

    public void setRevenueThisWeek(int revenueThisWeek) {
        this.revenueThisWeek = revenueThisWeek;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "topBusinesses=" + topBusinesses +
                ", totalUsers=" + totalUsers +
                ", newUsersThisWeek=" + newUsersThisWeek +
                ", totalBusinesses=" + totalBusinesses +
                ", newBusinessesToday=" + newBusinessesToday +
                ", totalRevenue=" + totalRevenue +
                ", revenueThisWeek=" + revenueThisWeek +
                '}';
    }
}
