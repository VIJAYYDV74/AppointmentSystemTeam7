package com.team7.appointmentsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "notificationtypes")
public class NotificationTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "notificationtype")
    private String notificationType;

    public NotificationTypes() {
    }

    public NotificationTypes(String notificationType) {
        this.notificationType = notificationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "NotificationTypes{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                '}';
    }
}
