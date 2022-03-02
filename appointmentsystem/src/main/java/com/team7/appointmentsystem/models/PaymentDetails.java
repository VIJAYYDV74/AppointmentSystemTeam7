package com.team7.appointmentsystem.models;

import com.team7.appointmentsystem.entity.BusinessAddress;
import com.team7.appointmentsystem.entity.Payments;
import com.team7.appointmentsystem.entity.Services;

import java.sql.Time;
import java.time.LocalDateTime;

public class PaymentDetails {
    private Payments payments;
    private String userName;
    private LocalDateTime bookedDate;
    private BusinessAddress businessAddress;
    private Services services;
    private Time beginTime;
    private Time endTime;

    public PaymentDetails(){

    }

    public PaymentDetails(Payments payments, String userName, LocalDateTime bookedDate,
                          BusinessAddress businessAddress, Services services, Time beginTime, Time endTime) {
        this.payments = payments;
        this.userName = userName;
        this.bookedDate = bookedDate;
        this.businessAddress = businessAddress;
        this.services = services;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDateTime bookedDate) {
        this.bookedDate = bookedDate;
    }

    public BusinessAddress getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(BusinessAddress businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
