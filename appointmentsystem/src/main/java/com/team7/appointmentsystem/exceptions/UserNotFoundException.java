package com.team7.appointmentsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class UserNotFoundException extends Exception{
    public UserNotFoundException(){

    }
    public UserNotFoundException(String s){
        super(s);
    }

}
