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

    @Column(name = "isblocked", columnDefinition = "boolean default false")
    private boolean isBlocked;

    public Services() {
    }

    public Services(String serviceName, String serviceDesc, int servicePrice, Business business) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.servicePrice = servicePrice;
        this.business = business;
        this.isBlocked = false;
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceid=" + serviceid +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", servicePrice=" + servicePrice +
                ", business=" + business +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
