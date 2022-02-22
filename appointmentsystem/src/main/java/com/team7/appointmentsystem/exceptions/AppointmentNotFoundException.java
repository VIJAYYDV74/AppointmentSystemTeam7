package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class AppointmentNotFoundException extends Exception{
    public AppointmentNotFoundException() {
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
