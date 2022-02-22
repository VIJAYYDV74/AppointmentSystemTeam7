package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public
class InternalServerException extends Exception{
    public InternalServerException() {
    }

    public InternalServerException(String message) {
        super(message);
    }
}
