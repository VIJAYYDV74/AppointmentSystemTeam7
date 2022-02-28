package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.resultapis.AppointmentSlots;
import com.team7.appointmentsystem.services.AppointmentService;
import com.team7.appointmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserNotificationService notificationService;

    @Transactional
    @PostMapping("/user/bookAppointment/{businessId}")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment,
                                                       @PathVariable Long businessId){
        Appointment appointment1 =  appointmentService.bookAppointment(appointment, businessId);
        return ResponseEntity.ok(appointment1);
    }

    @Transactional
    @GetMapping("/business/getAppointments/{businessId}")
    public List<Appointment> getBusinessAppointments(@PathVariable long businessId){
        return appointmentService.getBusinessAppointments(businessId);
    }

    @Transactional
    @GetMapping("/user/getAppointments/{userId}")
    public List<Appointment> getUserAppointments(@PathVariable long userId){
        return appointmentService.getUserAppointments(userId);
    }

    @Transactional
    @PostMapping("/user/cancelAppointment/{appointmentId}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable long appointmentId, @RequestBody String cancellationReason) {
        Appointment msg = appointmentService.cancelAppointment(appointmentId, cancellationReason);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(value = "user/bookAppointment/{businessId}", params = "date")
    public List<AppointmentSlots> appointmentPage(@PathVariable long businessId,
                                                  @RequestBody @DateTimeFormat(pattern = "MMddyyyy") Date date){
        System.out.println("hiowefh");
        return appointmentService.AppointmentsPage(businessId, date);
    }
}
