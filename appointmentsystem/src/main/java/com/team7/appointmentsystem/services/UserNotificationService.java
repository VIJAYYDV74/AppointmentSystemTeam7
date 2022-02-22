package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.entity.NotificationTypes;
import com.team7.appointmentsystem.entity.UserNotifications;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserNotificationService {

    @Autowired
    private UserNotificationsRepository usernotificationsRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserNotificationService.class);

    public List<UserNotifications> getUserNotifications(long userId) {
        try{
            List<UserNotifications> resultList = usernotificationsRepository.findByUsersUserid(userId);
            return resultList;
        } catch (Exception e) {
            logger.error("Exception occur while fetch Notification by user", e);
            return null;
        }
    }

    public void sendNotification(UserNotifications userNotifications){
        try {
            UserNotifications userNotifications1 = usernotificationsRepository.save(userNotifications);
            if (userNotifications1==null){
                throw new InternalServerException("InternalServerException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void sendUserNotificationOnAppointmentBooking(Appointment appointment) {
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        UserNotifications userNotifications = new UserNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Booked Successfully",
                "You have booked an appointment with " + appointment.getBusiness().getBusinessName() + " on " +
                        appointment.getAppointmentDate(),
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(userNotifications);
    }

    public void sendUserNotificationOnAppointmentCancelling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        UserNotifications userNotifications = new UserNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Cancelled",
                "Your appointment with " + appointment.getBusiness().getBusinessName() + " on " +
                        appointment.getAppointmentDate() + " has been cancelled.",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(userNotifications);
    }

    public void sendUserNotificationOnAppointmentRescheduling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        UserNotifications userNotifications = new UserNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Rescheduled",
                "Your appointment with " + appointment.getBusiness().getBusinessName() +
                        " has been rescheduled to " + appointment.getAppointmentDate() + ".",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(userNotifications);
    }

    public void sendUserNotificationOnPaymentDone(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(2);
        UserNotifications userNotifications = new UserNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Payment Done",
                "Your payment of " + appointment.getServices().getServicePrice() +
                        " to " + appointment.getBusiness().getBusinessName()+ " has been successfully completed.",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(userNotifications);
    }

    public UserNotifications getNotification(long notificationId) {
        UserNotifications userNotifications = usernotificationsRepository.findById(notificationId).orElse(null);
        userNotifications.setState(true);
        UserNotifications userNotifications1 = usernotificationsRepository.save(userNotifications);
        return userNotifications1;
    }
}
