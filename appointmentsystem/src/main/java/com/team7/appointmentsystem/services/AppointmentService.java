package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.exceptions.ServiceNotFoundException;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.repository.*;
import com.team7.appointmentsystem.resultapis.AppointmentSlots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private NotificationService notificationService;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    @Autowired
    private BusinessWorkingHoursRepository businessWorkingHoursRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};



    //get the userid from the session object and use it.
//    public String bookAppointment(Appointment appointment, Long businessId, Long userId) {

    public String bookAppointment(Appointment appointment, Long businessId, Long userId) {
        System.out.println(appointment.toString());
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
            Users users = userRepository.findByUserid(userId);

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
                notificationService.sendNotificationOnPaymentDone(appointment);
                if (payments1 == null) {
                    throw new InternalServerException("InternalServerException");
                }
                BillingDetails billingDetails = payments.getBillingDetails();
                BillingDetails billingDetails1 = billingDetailsRepository.save(billingDetails);
                if (billingDetails1==null){
                    throw new InternalServerException("InternalServerException");
                }
            }
            notificationService.sendNotificationOnPaymentDone(appointment);
            Appointment appointment1 = appointmentRepository.save(appointment);
            if (appointment1==null){
                throw new InternalServerException("InternalServerException");
            }

            notificationService.sendNotificationOnAppointmentBooking(appointment);

            return "Appointment booked!!!";
        }catch (Exception e){
//            e.printStackTrace();
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
        List<Appointment> appointments =  appointmentRepository.findByUsersUserid(userId);
        return appointments;
    }
//
//    public String makeAppointmentCompleted(long appointmentId){
//        Appointment appointment = appointmentRepository.getById(appointmentId);
//        appointment.setStatus("Completed");
//        appointmentRepository.save(appointment);
//        return "Completed";
//    }


    public StrObject cancelAppointment(long appointmentId, String cancellationReason){
        try{
            Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
            if(appointment==null){
                throw new AppointmentNotFoundException("Appointment not found");
            }
            appointment.setCancelled(true);
            appointment.setCancellationReason(cancellationReason);
            Appointment appointment1 = appointmentRepository.save(appointment);
            if (appointment1==null){
                throw new InternalServerException("InternalServerException");
            }
            else {
                notificationService.sendNotificationOnAppointmentCancelling(appointment1);
            }
            return new StrObject("Appointment Cancelled!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new StrObject(e.getMessage());
        }
    }

    public List<AppointmentSlots> AppointmentsPage(long businessId, String date) throws ParseException {
        System.out.println(date);
        Business business = businessRepository.getById(businessId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        List<Appointment> appointments = appointmentRepository.
                findByBusinessBusinessidAndAppointmentDate(businessId, date1);
        int slotDuration = business.getSlotDuration();
        BusinessWorkingHours businessWorkingHours =
                businessWorkingHoursRepository.findByBusinessBusinessidAndNameOfDay(businessId, "Monday");

        List<AppointmentSlots> appointmentSlots = new ArrayList<>();
        Time begin = businessWorkingHours.getStartHour();
        Time end = businessWorkingHours.getEndHour();

        while (begin.before(end)){
            Time temp = Time.valueOf(begin.toLocalTime().plusMinutes(slotDuration));
            AppointmentSlots appointmentSlots1 =
                    new AppointmentSlots(begin, temp);
            appointmentSlots.add(appointmentSlots1);
            begin = temp;
        }

        for (Appointment appointment:appointments) {
            Time t1 = appointment.getBeginTime();
            for (AppointmentSlots appointmentSlot:appointmentSlots) {
                if (appointmentSlot.getBeginTime().equals(t1)){
                    appointmentSlot.setAvailable(false);
                }
            }
        }
        return appointmentSlots;
    }
}