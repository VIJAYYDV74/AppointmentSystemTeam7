package com.team7.appointmentsystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class BusinessNotFoundException extends Exception{
    public BusinessNotFoundException() {
    }

    public BusinessNotFoundException(String message) {
        super(message);
    }
}
