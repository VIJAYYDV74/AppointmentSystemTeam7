package com.team7.appointmentsystem.models.AdminDashboardModels;

public class AllUsers {
    public String userName;
    public String userEmail;
    public String createdDate;
    public Long userId;

    public AllUsers() {
    }

    public AllUsers(String userName, String userEmail, String createdDate, Long userId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AllUsers{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", userId=" + userId +
                '}';
    }
}
