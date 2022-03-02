package com.team7.appointmentsystem.models.UserDashboard;

public class TotalAppointments {

    public String bookedDate;
    public  String appointedDate;
    public  String beginTime,endTime;
    public  int price;
    public  String businessName;
    public  String serviceName;
    public String paymentDate;
    public  String paymentMethod;

    public TotalAppointments() {
    }

    public TotalAppointments(String bookedDate, String appointedDate, String beginTime, String endTime, int price, String businessName, String serviceName, String paymentDate, String paymentMethod) {
        this.bookedDate = bookedDate;
        this.appointedDate = appointedDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.price = price;
        this.businessName = businessName;
        this.serviceName = serviceName;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getAppointedDate() {
        return appointedDate;
    }

    public void setAppointedDate(String appointedDate) {
        this.appointedDate = appointedDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "TotalAppointments{" +
                "bookedDate='" + bookedDate + '\'' +
                ", appointedDate='" + appointedDate + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                ", businessName='" + businessName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
