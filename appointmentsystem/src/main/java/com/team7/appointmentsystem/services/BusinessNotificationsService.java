package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.BusinessNotFoundException;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.BusinessNotificationsRepository;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.NotificationTypeRepository;
import com.team7.appointmentsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusinessNotificationsService {

    @Autowired
    private BusinessNotificationsRepository businessNotificationsRepository;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(BusinessNotificationsService.class);

    public List<BusinessNotifications> getBusinessNotifications(long businessId) {
        try{
            return businessNotificationsRepository.findByBusinessBusinessid(businessId);
        } catch (Exception e) {
            logger.error("Exception occur while fetch Notification by user", e);
            return null;
        }
    }

    public void sendNotification(BusinessNotifications businessNotifications){
        try {
            BusinessNotifications businessNotifications1 = businessNotificationsRepository.save(businessNotifications);
            if (businessNotifications1==null){
                throw new InternalServerException("InternalServerException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void sendBusinessNotificationOnAppointmentBooking(Appointment appointment) {
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        BusinessNotifications businessNotifications = new BusinessNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Received",
                "Appointment booked by " + appointment.getUsers().getFirstName() + " on " +
                        appointment.getAppointmentDate() + " for service " +
                        appointment.getServices().getServiceName(),
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public void sendBusinessNotificationOnAppointmentCancelling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        BusinessNotifications businessNotifications = new BusinessNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Cancelled",
                "Your appointment with " + appointment.getUsers().getFirstName() + " on " +
                        appointment.getAppointmentDate() + " has been cancelled.",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public void sendBusinessNotificationOnAppointmentRescheduling(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        BusinessNotifications businessNotifications = new BusinessNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment Rescheduled",
                "Your appointment with " + appointment.getUsers().getFirstName() +
                        " has been rescheduled to " + appointment.getAppointmentDate() + ".",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public void sendBusinessNotificationOnPaymentDone(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(2);
        BusinessNotifications businessNotifications = new BusinessNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Payment Received",
                "A payment of " + appointment.getServices().getServicePrice() +
                        " to " + appointment.getBusiness().getBusinessName()+ " has been received for service " +
                        appointment.getServices().getServiceName() + ".",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public void sendBusinessNotificationOnAppointmentCompleted(Appointment appointment){
        NotificationTypes notificationTypes = notificationTypeRepository.getById(1);
        BusinessNotifications businessNotifications = new BusinessNotifications(
                appointment.getUsers(),
                appointment.getBusiness(),
                "Appointment completed",
                "Your have completed an Appointment with " + appointment.getUsers().getFirstName() +
                        " for service "+ appointment.getServices().getServiceName() +" has been completed.",
                appointment,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public void sendBusinessNotificationOnBusinessSearched(long businessId, long userId){
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
        BusinessNotifications businessNotifications = new BusinessNotifications(
                users,
                business,
                "Business Searched",
                "Your business page has been recently visited.",
                null,
                LocalDateTime.now(),
                false,
                notificationTypes
        );
        sendNotification(businessNotifications);
    }

    public BusinessNotifications getNotification(long notificationId) {
        BusinessNotifications businessNotifications = businessNotificationsRepository.findById(notificationId).orElse(null);
        businessNotifications.setState(true);
        BusinessNotifications businessNotifications1 = businessNotificationsRepository.save(businessNotifications);
        return businessNotifications1;
    }

}
