package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.BusinessNotifications;
import com.team7.appointmentsystem.entity.UserNotifications;
import com.team7.appointmentsystem.services.BusinessNotificationsService;
import com.team7.appointmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserNotifications> getUserNotifications(@PathVariable long userId){
        return userNotificationService.getUserNotifications(userId);
    }

    @GetMapping("/business/notifications/{businessid}")
    public List<BusinessNotifications> getBusinessNotifications(@PathVariable long businessId) {
        return businessNotificationsService.getBusinessNotifications(businessId);
    }

}
