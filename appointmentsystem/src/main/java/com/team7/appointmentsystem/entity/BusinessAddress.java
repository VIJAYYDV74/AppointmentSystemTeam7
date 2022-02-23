package com.team7.appointmentsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "businessaddresses")
public class BusinessAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressid;

    @Column(name = "addressline1")
    private String addressLine1;

    @Column(name = "addressline2")
    private String addressLine2;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "langitude")
    private double longitude;

    public BusinessAddress() {
    }

    public BusinessAddress(String addressLine1, String addressLine2,
                           String postalCode, String state, String country,
                           double latitude, double longitude) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getAddressid() {
        return addressid;
    }

    public void setAddressid(long addressid) {
        this.addressid = addressid;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BusinessAddress{" +
                "businessid=" + addressid +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
