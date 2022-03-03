package com.team7.appointmentsystem.models.AdminDashboardModels;

public class AllBusineesses {
    public String businessName;
    public String businessEmail;

    public int saleValue;
    public String categoryName;
    public int rating;


    public AllBusineesses() {
    }

    public AllBusineesses(String businessName, String businessEmail, int saleValue, String categoryName, int rating) {
        this.businessName = businessName;
        this.businessEmail = businessEmail;
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

    @Override
    public String toString() {
        return "AllBusineesses{" +
                "businessName='" + businessName + '\'' +
                ", businessEmail='" + businessEmail + '\'' +
                ", saleValue=" + saleValue +
                ", categoryName='" + categoryName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
