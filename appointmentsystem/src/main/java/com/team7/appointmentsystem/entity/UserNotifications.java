package com.team7.appointmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "notifications")
public class UserNotifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "businessid")
    private Business business;

    @Column(name = "header")
    private String header;

    @Column(name = "body")
    private String body;

    @OneToOne
    @JoinColumn(name = "appointmentid")
    private Appointment appointment;

    @Column(name = "createdtime", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdTime;

    @Column(name = "state", columnDefinition = "boolean not null default false")
    private boolean state;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "notificationtype")
    private NotificationTypes notificationTypes;

    public UserNotifications() {
    }

    public UserNotifications(Users users, Business business, String header, String body,
                             Appointment appointment, LocalDateTime createdTime, boolean state,
                             NotificationTypes notificationTypes) {
        this.users = users;
        this.business = business;
        this.header = header;
        this.body = body;
        this.appointment = appointment;
        this.createdTime = createdTime;
        this.state = state;
        this.notificationTypes = notificationTypes;
    }

    public long getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(long notificationid) {
        this.notificationid = notificationid;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public NotificationTypes getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(NotificationTypes notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    @Override
    public String toString() {
        return "UserNotifications{" +
                "notificationid=" + notificationid +
                ", users=" + users +
                ", business=" + business +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", appointment=" + appointment +
                ", createdTime=" + createdTime +
                ", state=" + state +
                ", notificationTypes=" + notificationTypes +
                '}';
    }
}
