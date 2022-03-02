package com.team7.appointmentsystem.models.AdminDashboardModels;

public class AllBusineesses {
    public String businessName;
    public String popularService;
    public int saleValue;
    public String categoryName;
    public int rating;


    public AllBusineesses() {
    }

    public AllBusineesses(String businessName, String popularService, int saleValue, String categoryName, int rating) {
        this.businessName = businessName;
        this.popularService = popularService;
        this.saleValue = saleValue;
        this.categoryName = categoryName;
        this.rating = rating;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPopularService() {
        return popularService;
    }

    public void setPopularService(String popularService) {
        this.popularService = popularService;
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

    @Override
    public String toString() {
        return "AllBusineesses{" +
                "businessName='" + businessName + '\'' +
                ", popularService='" + popularService + '\'' +
                ", saleValue=" + saleValue +
                ", categoryName='" + categoryName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
