package com.team7.appointmentsystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class WorkingHoursException extends Exception{
    public WorkingHoursException() {
    }

    public WorkingHoursException(String message) {
        super(message);
    }
}
