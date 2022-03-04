package com.team7.appointmentsystem.models.BusinessDetails;

public class Reviewer {

    public String userName;
    public String userImage;
    public int rating;
    public String feedback;


    public Reviewer() {
    }

    public Reviewer(String userName, String userImage, int rating, String feedback) {
        this.userName = userName;
        this.userImage = userImage;
        this.rating = rating;
        this.feedback = feedback;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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
}
