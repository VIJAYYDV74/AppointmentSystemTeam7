package com.team7.appointmentsystem.models.AdminDashboardModels;

public class TopBusinesses {
    public String businessName;
    public String service;
    public int saleValue;
    public String category;


    public TopBusinesses(){

    }
    public TopBusinesses(String businessName, String service, int saleValue, String category) {
        this.businessName = businessName;
        this.service = service;
        this.saleValue = saleValue;
        this.category = category;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
