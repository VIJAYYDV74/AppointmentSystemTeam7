package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.BusinessNotFoundException;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public List<NotificationsTable> getUserNotifications(long userId) {
        try{
            List<NotificationsTable> resultList = notificationsRepository.findBySenderUserid(userId);
            return resultList;
        } catch (Exception e) {
            logger.error("Exception occur while fetch Notification by user", e);
            return null;
        }
    }

    public void sendNotification(NotificationsTable notificationsTable){
        try {
            NotificationsTable notificationsTable1 = notificationsRepository.save(notificationsTable);
            if (notificationsTable1==null){
                throw new InternalServerException("InternalServerException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void sendNotificationOnAppointmentBooking(Appointment appointment) {
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Appointment Booked Successfully",
                "You have booked an appointment with " + appointment.getBusiness().getBusinessName() + " on " +
                        appointment.getAppointmentDate(),
                appointment.getBusiness().getUsers(),
                appointment.getUsers(),
                false,
                notificationTypes
        );
        NotificationsTable notificationsTable1 = new NotificationsTable(
                "Appointment Received",
                "Appointment booked by " + appointment.getUsers().getFirstName() + " on " +
                        appointment.getAppointmentDate() + " for service " +
                        appointment.getServices().getServiceName(),
                appointment.getUsers(),
                appointment.getBusiness().getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
        sendNotification(notificationsTable1);
    }

    public void sendNotificationOnAppointmentCancelling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Appointment Cancelled",
                "Your appointment with " + appointment.getBusiness().getBusinessName() + " on " +
                       appointment.getAppointmentDate() + " has been cancelled.",
                appointment.getBusiness().getUsers(),
                appointment.getUsers(),
                false,
                notificationTypes
        );
        NotificationsTable notificationsTable1 = new NotificationsTable(
                "Appointment Cancelled",
                "Your appointment with " + appointment.getUsers().getFirstName() + " on " +
                        appointment.getAppointmentDate() + " has been cancelled.",
                appointment.getUsers(),
                appointment.getBusiness().getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
        sendNotification(notificationsTable1);
    }

    public void sendNotificationOnAppointmentRescheduling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Appointment Rescheduled",
                "Your appointment with " + appointment.getBusiness().getBusinessName() +
                        " has been rescheduled to " + appointment.getAppointmentDate() + ".",
                appointment.getBusiness().getUsers(),
                appointment.getUsers(),
                false,
                notificationTypes
        );
        NotificationsTable notificationsTable1 = new NotificationsTable(
                "Appointment Rescheduled",
                "Your appointment with " + appointment.getUsers().getFirstName() +
                        " has been rescheduled to " + appointment.getAppointmentDate() + ".",
                appointment.getUsers(),
                appointment.getBusiness().getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
        sendNotification(notificationsTable1);
    }

    public void sendNotificationOnAppointmentCompleted(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Appointment completed",
                "Your Appointment with " + appointment.getBusiness().getBusinessName() +
                        " has been completed. Please leave a review and comment about our service.",
                appointment.getBusiness().getUsers(),
                appointment.getUsers(),
                false,
                notificationTypes
        );
        NotificationsTable notificationsTable1 = new NotificationsTable(
                "Appointment completed",
                "Your have completed an Appointment with " + appointment.getUsers().getFirstName() +
                        " for service "+ appointment.getServices().getServiceName() +" has been completed.",
                appointment.getUsers(),
                appointment.getBusiness().getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
        sendNotification(notificationsTable1);
    }

    public void sendNotificationOnPaymentDone(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(2);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Payment Done",
                "Your payment of " + appointment.getServices().getServicePrice() +
                        " to " + appointment.getBusiness().getBusinessName()+ " has been successfully completed.",
                appointment.getBusiness().getUsers(),
                appointment.getUsers(),
                false,
                notificationTypes
        );
        NotificationsTable notificationsTable1 = new NotificationsTable(
                "Payment Received",
                "A payment of " + appointment.getServices().getServicePrice() +
                        " to " + appointment.getBusiness().getBusinessName()+ " has been received for service " +
                        appointment.getServices().getServiceName() + ".",
                appointment.getUsers(),
                appointment.getBusiness().getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
        sendNotification(notificationsTable1);
    }

    public NotificationsTable getNotification(long notificationId) {
        NotificationsTable notificationsTable = notificationsRepository.findById(notificationId).orElse(null);
        notificationsTable.setState(true);
        NotificationsTable notificationsTable1 = notificationsRepository.save(notificationsTable);
        return notificationsTable1;
    }

    public void sendNotificationOnBusinessSearched(long businessId, long userId){
        Business business = businessRepository.findById(businessId).orElse(null);
        try {
            if (business == null) {
                throw new BusinessNotFoundException("BusinessNotFoundException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        Users users = userRepository.findById(userId).orElse(null);
        NotificationTypes notificationTypes = notificationTypeRepository.getById(3);
        NotificationsTable notificationsTable = new NotificationsTable(
                "Business Searched",
                "Your business page has been recently visited.",
                null,
                business.getUsers(),
                false,
                notificationTypes
        );
        sendNotification(notificationsTable);
    }
}
