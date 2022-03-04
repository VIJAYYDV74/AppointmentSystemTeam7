package com.team7.appointmentsystem.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class NotificationsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "header")
    private String header;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "sender")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private Users receiver;

    @Column(name = "createdtime")
    private LocalDateTime localDateTime;

    @Column(name = "state")
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "notificationtype")
    private NotificationTypes notificationTypes;

    public NotificationsTable() {
    }

    public NotificationsTable(String header, String message, Users sender, Users receiver,
                              boolean state, NotificationTypes notificationTypes) {
        this.header = header;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.localDateTime = LocalDateTime.now();
        this.state = state;
        this.notificationTypes = notificationTypes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getUsers() {
        return receiver;
    }

    public void setUsers(Users users) {
        this.receiver = users;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
}