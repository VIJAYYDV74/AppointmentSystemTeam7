package com.team7.appointmentsystem.miscellinious;

import com.team7.appointmentsystem.entity.*;

import java.util.List;

public interface BusinessDetails {
    long getBusinessid();
    String getBusinessName();
    String getBusinessDescription();
    String getBusinessTitle();
    String getBusinessMobileNumber();
    String getBusinessEmail();
    boolean isCancellationAvailable();
    GenderCategories getGenderCategory();
    Categories getCategories();
    List<Services> getServices();
    BusinessAddress getBusinessAddress();
    interface BusinessAddress{
        String getPostalCode();
    }
    Users getUsers();
    interface Users{
        long getUserid();
    }
    List<Comments> getComments();
    String getBusinessRating();
    List<BusinessImages> getBusinessImages();
    boolean isBlocked();
}
