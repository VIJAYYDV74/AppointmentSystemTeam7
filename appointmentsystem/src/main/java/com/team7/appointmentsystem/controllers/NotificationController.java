package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.NotificationsTable;
import com.team7.appointmentsystem.services.NotificationService;
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
    private NotificationService notificationService;

    @GetMapping("/user/notifications/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<NotificationsTable>> getUserNotifications(@PathVariable long userId){
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @GetMapping("user/notifications/notification/{notificationId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<NotificationsTable> getUserNotification(@PathVariable long notificationId){
        NotificationsTable userNotifications = notificationService.getNotification(notificationId);
        return ResponseEntity.ok(userNotifications);
    }
}
