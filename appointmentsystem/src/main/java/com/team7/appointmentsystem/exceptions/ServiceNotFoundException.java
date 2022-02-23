package com.team7.appointmentsystem.exceptions;

public class ServiceNotFoundException extends Exception{
    public ServiceNotFoundException() {
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
