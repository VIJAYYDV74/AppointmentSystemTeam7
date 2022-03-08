package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.models.TimeSlot;
import com.team7.appointmentsystem.resultapis.AppointmentSlots;
import com.team7.appointmentsystem.services.AppointmentService;
import com.team7.appointmentsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    @PostMapping("/user/bookAppointment/{businessId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<StrObject> bookAppointment(@RequestBody Appointment appointment,
                                                       @PathVariable Long businessId,
                                                       @RequestParam Long userId){
        StrObject appointment1 =  new StrObject(appointmentService.bookAppointment(appointment, businessId, userId));
        return ResponseEntity.ok(appointment1);
    }

    @Transactional
    @GetMapping("/business/getAppointments/{businessId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Appointment> getBusinessAppointments(@PathVariable long businessId){
        return appointmentService.getBusinessAppointments(businessId);
    }

    @Transactional
    @GetMapping("/user/getAppointments/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Appointment> getUserAppointments(@PathVariable long userId){
        return appointmentService.getUserAppointments(userId);
    }

    @Transactional
    @PostMapping("/user/cancelAppointment/{appointmentId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<StrObject> cancelAppointment(@PathVariable long appointmentId, @RequestBody String cancellationReason) {
        StrObject msg = appointmentService.cancelAppointment(appointmentId, cancellationReason);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(value = "user/bookAppointment/{businessId}", params = "date")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<AppointmentSlots> appointmentPage(@PathVariable long businessId,
                                                  @RequestParam String date) throws ParseException {
        return appointmentService.AppointmentsPage(businessId, date);
    }

    @Transactional
    @PostMapping(value = "/user/reschedule/{appointmentId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<StrObject> reschedule(@RequestBody Appointment newAppointment,
                                                @PathVariable Long appointmentId) {
        StrObject msg = appointmentService.reschedule(newAppointment, appointmentId);
        return ResponseEntity.ok(msg);
    }

}
