package com.team7.appointmentsystem.metamodels;

import com.team7.appointmentsystem.entity.*;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.List;

@StaticMetamodel(Business.class)
public class Business_ {
    public static volatile SingularAttribute<Business, Long> businessid;
    public static volatile SingularAttribute<Business, String> businessName;
    public static volatile SingularAttribute<Business, String> businessTitle;
    public static volatile SingularAttribute<Business, String> businessDescription;
    public static volatile SingularAttribute<Business, String> businessMobileNumber;
    public static volatile SingularAttribute<Business, String> businessEmail;
    public static volatile SingularAttribute<Business, Boolean> cancellationAvailable;
    public static volatile SingularAttribute<Business, Integer> slotDuration;
    public static volatile SingularAttribute<Business, String> genderCategory;
    public static volatile SingularAttribute<Business, String> emailVerified;
    public static volatile SingularAttribute<Business, LocalDateTime> createdTime;
    public static volatile SingularAttribute<Business, String> businessImages;
    public static volatile SingularAttribute<Business, Categories> categories;
    public static volatile SingularAttribute<Business, Users> users;
    public static volatile SingularAttribute<Business, List<BusinessWorkingHours>> workingHours;
    public static volatile SingularAttribute<Business, BusinessAddress> businessAddress;
    public static volatile SingularAttribute<Business, List<Services>> services;

    public static final String BUSINESS_EMAIL = "businessEmail";
    public static final String BUSINESS_DESCRIPTION = "businessDescription";
    public static final String BUSINESSID = "businessid";
    public static final String BUSINESS_NAME = "businessName";
    public static final String SERVICES = "services";
    public static final String USERS = "users";
    public static final String CANCELLATION_AVAILABLE = "cancellationAvailable";
    public static final String EMAIL_VERIFIED = "emailVerified";
    public static final String SLOT_DURATION = "slotDuration";
    public static final String BUSINESS_TITLE = "businessTitle";
    public static final String BUSINESS_MOBILE_NUMBER = "businessMobileNumber";
    public static final String GENDER_CATEGORY = "genderCategory";
    public static final String CREATED_TIME = "createdTime";
    public static final String CATEGORIES = "categories";
    public static final String BUSINESS_ADDRESS = "businessAddress";
    public static final String BUSINESS_IMAGES = "businessImages";
    public static final String WORKING_HOURS = "workingHours";
}
