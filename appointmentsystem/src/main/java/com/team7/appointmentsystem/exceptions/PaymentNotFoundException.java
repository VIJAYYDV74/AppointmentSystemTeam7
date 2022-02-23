package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class PaymentNotFoundException extends Exception{

    public PaymentNotFoundException() {
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}