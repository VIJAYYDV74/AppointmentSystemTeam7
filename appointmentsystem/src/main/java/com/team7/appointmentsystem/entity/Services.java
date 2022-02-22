package com.team7.appointmentsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceid;

    @Column(name = "servicename")
    private String serviceName;

    @Column(name = "servicedesc")
    private String serviceDesc;

    @Column(name = "serviceprice")
    private int servicePrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "businessid")
    private Business business;

    public Services() {
    }

    public Services(String serviceName, String serviceDesc, int servicePrice, Business business) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.servicePrice = servicePrice;
        this.business = business;
    }

    public long getServiceid() {
        return serviceid;
    }

    public void setServiceid(long serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceid=" + serviceid +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", servicePrice=" + servicePrice +
                ", business=" + business +
                '}';
    }
}
