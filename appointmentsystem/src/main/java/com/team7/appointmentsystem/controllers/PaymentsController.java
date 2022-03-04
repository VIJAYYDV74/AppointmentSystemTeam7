package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Payments;
import com.team7.appointmentsystem.models.PaymentDetails;
import com.team7.appointmentsystem.services.PaymentsService;
import com.team7.appointmentsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("user/getPaymentDetails/{appointmentId}/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<PaymentDetails> getPaymentDetailsForUser(@PathVariable long appointmentId){
        return ResponseEntity.ok(paymentsService.paymentDetails(appointmentId));
    }

    @GetMapping("business/getPaymentDetails/{appointmentId}/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<PaymentDetails> getPaymentDetailsForBusiness(@PathVariable long appointmentId){
        return ResponseEntity.ok(paymentsService.paymentDetails(appointmentId));
    }

    @Transactional
    @PostMapping("/user/makePayment/{appointmentId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> makePayment(@PathVariable long appointmentId, @RequestBody Payments payments){
        String paymentDone =  paymentsService.makePayment(appointmentId, payments);
        return ResponseEntity.ok(paymentDone);
    }

}
