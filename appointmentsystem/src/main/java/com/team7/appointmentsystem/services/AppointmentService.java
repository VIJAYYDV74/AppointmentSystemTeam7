package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.exceptions.ServiceNotFoundException;
import com.team7.appointmentsystem.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private UserNotificationService userNotificationService;

    @Autowired
    private UserNotificationService notificationService;

    @Autowired
    private BusinessNotificationsService businessNotificationsService;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    //get the userid from the session object and use it.
    public Appointment bookAppointment(Appointment appointment, Long businessId) {
        try{
            Payments payments = appointment.getPayments();
            Services services = servicesRepository.findById(appointment.getServices().
                    getServiceid()).orElse(null);

            if (services==null){
                throw new ServiceNotFoundException("ServiceNotFoundException");
            }

            appointment.setBookedDate(LocalDateTime.now());
            //get the user id value from session object.
            //example case taken userid as 1
            Users users = userRepository.findByUserid(1);

            Business business = businessRepository.getById(businessId);

            appointment.setTotalPrice(services.getServicePrice());
            appointment.setUsers(users);
            appointment.setBusiness(business);
            appointment.setServices(services);

            if (payments!=null){
                payments.setPaymentDate(LocalDateTime.now());
                payments.setAmount(services.getServicePrice());
                Payments payments1 = paymentsRepository.save(payments);
                appointment.setPayments(payments1);
                userNotificationService.sendUserNotificationOnPaymentDone(appointment);
                if (payments1 == null) {
                    throw new InternalServerException("InternalServerException");
                }
                BillingDetails billingDetails = payments.getBillingDetails();
                BillingDetails billingDetails1 = billingDetailsRepository.save(billingDetails);
                if (billingDetails1==null){
                    throw new InternalServerException("InternalServerException");
                }
            }
            businessNotificationsService.sendBusinessNotificationOnPaymentDone(appointment);
            Appointment appointment1 = appointmentRepository.save(appointment);
            if (appointment1==null){
                throw new InternalServerException("InternalServerException");
            }

            boolean b1 = userNotificationService.sendUserNotificationOnAppointmentBooking(appointment);
            boolean b2 = businessNotificationsService.sendBusinessNotificationOnAppointmentBooking(appointment);
            if (!b1){
                logger.error("Unable to send notification to user");
            }
            if (!b2){
                logger.error("Unable to send notification to business");
            }

            return appointment1;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Appointment> getBusinessAppointments(long businessId) {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findByBusinessBusinessid(businessId).forEach(appointments::add);
        return appointments;
    }

    public List<Appointment> getUserAppointments(long userId) {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findByUsersUserid(userId).forEach(appointments::add);
        return appointments;
    }
//
//    public String makeAppointmentCompleted(long appointmentId){
//        Appointment appointment = appointmentRepository.getById(appointmentId);
//        appointment.setStatus("Completed");
//        appointmentRepository.save(appointment);
//        return "Completed";
//    }

    public String cancelAppointment(long appointmentId, String cancellationReason){
        try{
            Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
            if(appointment==null){
                throw new AppointmentNotFoundException("Appointment not found");
            }
            appointment.setCancelled(true);
            appointment.setCancellationReason(cancellationReason);
            appointmentRepository.save(appointment);
            return "Appointment cancelled!!!";
        } catch (AppointmentNotFoundException e) {
            return e.getMessage();
        }
    }
}
