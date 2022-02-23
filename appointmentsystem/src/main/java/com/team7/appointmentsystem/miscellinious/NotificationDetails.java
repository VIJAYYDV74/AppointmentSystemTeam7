package com.team7.appointmentsystem.miscellinious;

public interface NotificationDetails {
    long getNotificationid();
    String getHeader();
    String getBody();
    boolean isState();
    NotificationTypes getNotificationTypes();
    interface NotificationTypes{
        String getNotificationType();
    }
}
