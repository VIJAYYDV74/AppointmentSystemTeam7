package com.team7.appointmentsystem.metamodels;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Services;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Business.class)
public class Services_ {
    public static volatile SingularAttribute<Services, String> serviceDesc;
    public static volatile SingularAttribute<Services, Business> business;
    public static volatile SingularAttribute<Services, Integer> servicePrice;
    public static volatile SingularAttribute<Services, Long> serviceid;
    public static volatile SingularAttribute<Services, String> serviceName;

    public static final String SERVICE_DESC = "serviceDesc";
    public static final String BUSINESS = "business";
    public static final String SERVICE_PRICE = "servicePrice";
    public static final String SERVICEID = "serviceid";
    public static final String SERVICE_NAME = "serviceName";
}
