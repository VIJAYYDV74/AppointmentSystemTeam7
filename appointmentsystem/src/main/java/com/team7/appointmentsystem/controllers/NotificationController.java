package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.BusinessNotifications;
import com.team7.appointmentsystem.entity.UserNotifications;
import com.team7.appointmentsystem.services.BusinessNotificationsService;
import com.team7.appointmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private UserNotificationService userNotificationService;

    @Autowired
    private BusinessNotificationsService businessNotificationsService;

    @GetMapping("/user/notifications/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<UserNotifications>> getUserNotifications(@PathVariable long userId){
        return ResponseEntity.ok(userNotificationService.getUserNotifications(userId));
    }

    @GetMapping("/business/notifications/{businessid}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<BusinessNotifications>> getBusinessNotifications(@PathVariable long businessId) {
        return ResponseEntity.ok(businessNotificationsService.getBusinessNotifications(businessId));
    }

    @GetMapping("/user/notifications/notification/{notificationId}")
    public ResponseEntity<UserNotifications> getUserNotification(@PathVariable long notificationId){
        UserNotifications userNotifications = userNotificationService.getNotification(notificationId);
        return ResponseEntity.ok(userNotifications);
    }

    @GetMapping("/business/notifications/notification/{notificationId}")
    public BusinessNotifications getBusinessNotification(@PathVariable long notificationId){
        BusinessNotifications businessNotifications  = businessNotificationsService.getNotification(notificationId);
        return businessNotifications;
    }
}
