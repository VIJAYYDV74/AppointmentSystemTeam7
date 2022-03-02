package com.team7.appointmentsystem.models.UserDashboard;

public class Reviews {
    public int rating;
    public  String feedback;
    public String businessName;
    public String Date;

    public Reviews() {
    }

    public Reviews(int rating, String feedback, String businessName, String date) {
        this.rating = rating;
        this.feedback = feedback;
        this.businessName = businessName;
        Date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "rating=" + rating +
                ", feedback='" + feedback + '\'' +
                ", businessName='" + businessName + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
