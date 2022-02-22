package com.team7.appointmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long userid;

    @Column(columnDefinition = "varchar(50)", name = "firstname", nullable = false)
    private String firstName;

    @Column(columnDefinition = "varchar(50)", name = "lastname", nullable = false)
    private String lastName;

    @Column(columnDefinition = "varchar(255) not null",nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "userpassword")
    private String userPassword;

    @Column(nullable = false, name = "mobilenumber")
    private String mobileNumber;

    @Column(columnDefinition = "timestamp default current_timestamp", name = "createdtime")
    private LocalDateTime createdTime;

    @Column(columnDefinition = "boolean default false", name = "isblocked")
    private boolean isBlocked;

    @Column(name = "profileimage")
    private String profileImage;

    @Column(columnDefinition = "boolean default false", name = "emailverified")
    private boolean emailVerified;


    public Users(){

    }

    public Users(String firstname, String lastName, String email,
                 String userPassword, String mobileNumber, String profileImage) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.userPassword = userPassword;
        this.mobileNumber = mobileNumber;
        this.profileImage = profileImage;
    }

    public Users(String firstname, String lastname, String email,
                 String userPassword, String mobileNumber, LocalDateTime createdTime,
                 boolean isBlocked, String profileImage, boolean emailVerified) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.userPassword = userPassword;
        this.mobileNumber = mobileNumber;
        this.createdTime = createdTime;
        this.isBlocked = isBlocked;
        this.profileImage = profileImage;
        this.emailVerified = emailVerified;
    }

    @Override
    public String toString() {
        return "Users{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", createdTime=" + createdTime +
                ", isBlocked=" + isBlocked +
                ", profileImage='" + profileImage + '\'' +
                ", emailVerified=" + emailVerified +
                '}';
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

}
