package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.services.AppointmentService;
import com.team7.appointmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserNotificationService notificationService;

    @Transactional
    @PostMapping("/user/bookAppointment/{businessId}")

    public String bookAppointment(@RequestBody Appointment appointment, @PathVariable Long businessId, @RequestParam Long userId){
        String s =  appointmentService.bookAppointment(appointment, businessId, userId).toString();
        return s;
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
    public ResponseEntity<StrObject> cancelAppointment(@PathVariable long appointmentId, @RequestBody String cancellationReason) {
        StrObject msg = appointmentService.cancelAppointment(appointmentId, cancellationReason);
        return ResponseEntity.ok(msg);
    }

//    @Transactional
//    @PostMapping("/user/bookAppointment/reschedule/{businessId}")
//    public ResponseEntity<String> rescheduleAppointment(
//            @RequestParam String toDate, @RequestParam Long appointmentId) throws AppointmentNotFoundException {
//        String msg = appointmentService.reschedule(toDate, appointmentId);
//        return ResponseEntity.ok(msg);
//    }

}
