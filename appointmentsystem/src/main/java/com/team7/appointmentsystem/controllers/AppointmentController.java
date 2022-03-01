package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.resultapis.AppointmentSlots;
import com.team7.appointmentsystem.services.AppointmentService;
import com.team7.appointmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserNotificationService notificationService;

    @Transactional
    @PostMapping("/user/bookAppointment/{businessId}")
    public String bookAppointment(@RequestBody Appointment appointment, @PathVariable Long businessId, @RequestParam Long userId) {
        String s = appointmentService.bookAppointment(appointment, businessId, userId).toString();
        return s;
    }
//    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment,
//                                                       @PathVariable Long businessId){
//        Appointment appointment1 =  appointmentService.bookAppointment(appointment, businessId);
//        return ResponseEntity.ok(appointment1);
//

//    @Transactional
//    @PostMapping("/user/bookAppointment/{businessId}")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment,
//                                                       @PathVariable Long businessId){
//        Appointment appointment1 =  appointmentService.bookAppointment(appointment, businessId);
//        return ResponseEntity.ok(appointment1);
//
//    }

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



//     @CrossOrigin(origins = "*", allowedHeaders = "*")
//     public ResponseEntity<Appointment> cancelAppointment(@PathVariable long appointmentId,
//                                                          @RequestBody String cancellationReason) {
//         Appointment msg = appointmentService.cancelAppointment(appointmentId, cancellationReason);
//         return ResponseEntity.ok(msg);
//     }

    @GetMapping(value = "user/bookAppointment/{businessId}", params = "date")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<AppointmentSlots> appointmentPage(@PathVariable long businessId,
                                                  @RequestParam String date) throws ParseException {
        return appointmentService.AppointmentsPage(businessId, date);
    }

}
