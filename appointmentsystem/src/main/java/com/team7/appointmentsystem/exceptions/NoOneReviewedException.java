package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class NoOneReviewedException extends Exception{
    public NoOneReviewedException(String message) {
        super(message);
    }
}
