package com.team7.appointmentsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "billingdetails")
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country", columnDefinition = "varchar(50)")
    private String country;

    @Column(name = "firstname", columnDefinition = "varchar(100)")
    private String firstName;

    @Column(name = "lastname", columnDefinition = "varchar(100)")
    private String lastName;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "city", columnDefinition = "varchar(128)")
    private String city;

    @Column(name = "state", columnDefinition = "varchar(100)")
    private String state;

    @Column(name = "postalcode", columnDefinition = "varchar(10)")
    private String postalCode;

    @Column(name = "billingemailid", columnDefinition = "varchar(100)")
    private String billingEmailId;

    @Column(name = "billingmobilenumber")
    private String billingMobileNumber;

    public BillingDetails() {
    }

    public BillingDetails(String country, String firstName, String lastName, String address,
                          String city, String state, String postalCode, String billingEmailId,
                          String billingMobileNumber) {
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.billingEmailId = billingEmailId;
        this.billingMobileNumber = billingMobileNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getBillingEmailId() {
        return billingEmailId;
    }

    public void setBillingEmailId(String billingEmailId) {
        this.billingEmailId = billingEmailId;
    }

    public String getBillingMobileNumber() {
        return billingMobileNumber;
    }

    public void setBillingMobileNumber(String billingMobileNumber) {
        this.billingMobileNumber = billingMobileNumber;
    }

    @Override
    public String toString() {
        return "BillingDetails{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", billingEmailId='" + billingEmailId + '\'' +
                ", billingMobileNumber='" + billingMobileNumber + '\'' +
                '}';
    }
}
