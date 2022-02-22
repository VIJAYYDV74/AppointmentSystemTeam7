package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Payments;
import com.team7.appointmentsystem.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping("user/getPaymentDetails/{appointmentId}/")
    public Object getPaymentDetailsForUser(@PathVariable long appointmentId){
        return paymentsService.paymentDetails(appointmentId);
    }

    @GetMapping("business/getPaymentDetails/{appointmentId}/")
    public Object getPaymentDetailsForBusiness(@PathVariable long appointmentId){
        return paymentsService.paymentDetails(appointmentId);
    }

    @Transactional
    @PostMapping("/user/makePayment/{appointmentId}")
    public String makePayment(@PathVariable long appointmentId, @RequestBody Payments payments){
        return paymentsService.makePayment(appointmentId, payments);
    }

}
