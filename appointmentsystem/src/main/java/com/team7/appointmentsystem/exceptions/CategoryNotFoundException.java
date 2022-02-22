package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String s) {
        super(s);
    }
}
