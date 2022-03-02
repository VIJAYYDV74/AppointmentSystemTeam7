package com.team7.appointmentsystem.models.AdminDashboardModels;

public class AllBooking {
    public String userName;
    public String businessName;
    public String serviceName;
    public int price;
    public String bookedDate;

    public AllBooking() {
    }

    public AllBooking(String userName, String businessName, String serviceName, int price, String bookedDate) {
        this.userName = userName;
        this.businessName = businessName;
        this.serviceName = serviceName;
        this.price = price;
        this.bookedDate = bookedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    @Override
    public String toString() {
        return "AllBooking{" +
                "userName='" + userName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", price=" + price +
                ", bookedDate='" + bookedDate + '\'' +
                '}';
    }
}
