package com.team7.appointmentsystem.models.AdminDashboardModels;

public class AllBusineesses {
    public Long businessId;
    public String businessName;
    public String businessEmail;

    public int saleValue;
    public String categoryName;
    public int rating;


    public AllBusineesses() {
    }

    public AllBusineesses(Long businessId, String businessName, String businessEmail, int saleValue, String categoryName, int rating) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.saleValue = saleValue;
        this.categoryName = categoryName;
        this.rating = rating;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
