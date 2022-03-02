package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.entity.Payments;
import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.exceptions.PaymentNotFoundException;
import com.team7.appointmentsystem.exceptions.ServiceNotFoundException;
import com.team7.appointmentsystem.models.PaymentDetails;
import com.team7.appointmentsystem.repository.AppointmentRepository;
import com.team7.appointmentsystem.repository.BillingDetailsRepository;
import com.team7.appointmentsystem.repository.PaymentsRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private UserNotificationService userNotificationService;

    @Autowired
    private BusinessNotificationsService businessNotificationsService;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    private static final Logger logger = LoggerFactory.getLogger(PaymentsService.class);

    public PaymentDetails paymentDetails(long appointmentId) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setPayments(appointment.getPayments());
            paymentDetails.setBeginTime(appointment.getBeginTime());
            paymentDetails.setEndTime(appointment.getEndTime());
            paymentDetails.setServices(appointment.getServices());
            paymentDetails.setBookedDate(appointment.getBookedDate());
            paymentDetails.setBusinessAddress(appointment.getBusiness().getBusinessAddress());
            paymentDetails.setUserName(appointment.getUsers().getFirstName());
            return paymentDetails;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public String completePayment(Appointment appointment, Payments payments){
        try {
            if (appointment==null){
                throw new AppointmentNotFoundException("AppointmentNotFoundException");
            }
            Payments payments1 = paymentsRepository.save(payments);
            if (payments1==null){
                throw new InternalServerException("InternalServerException");
            }
            appointment.setPayments(payments1);
            Appointment appointment1 = appointmentRepository.save(appointment);
            if (appointment1==null){
                throw new InternalServerException("InternalServerException");
            }
            return "Payment Done";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public String makePayment(long appointmentId, Payments payments) {
        try{
            Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
            if(appointment==null){
                throw new AppointmentNotFoundException("AppointmentNotFoundException");
            }
            Services services = servicesRepository.getById(appointment.getServices().getServiceid());
            if(services==null){
                throw new ServiceNotFoundException("ServiceNotFoundException");
            }
            payments.setAmount(services.getServicePrice());

            Payments payments1 = paymentsRepository.save(payments);
            if (payments1==null){
                throw new InternalServerException("InternalServerException");
            }

            userNotificationService.sendUserNotificationOnPaymentDone(appointment);
            businessNotificationsService.sendBusinessNotificationOnPaymentDone(appointment);

            appointment.setPayments(payments);
            Appointment appointment1 = appointmentRepository.save(appointment);
            if (appointment1==null){
                throw new InternalServerException("InternalServerException");
            }
            return "Payment Done!!";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
