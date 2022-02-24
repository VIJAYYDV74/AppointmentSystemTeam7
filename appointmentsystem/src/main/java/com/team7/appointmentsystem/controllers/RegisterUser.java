package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.team7.appointmentsystem.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisterUser {

    @Autowired
    private RegisterService registerService;


    @GetMapping("/showUsers")
    public List<Users> showUsers(){
        return registerService.showUsers();
    }

    @GetMapping("/register")
    public String signUpForm() {
        return "SignupForm";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) throws UserAlreadyExistsException {
        System.out.println(user);
        String msg = registerService.registerUser(user);
        return ResponseEntity.ok(msg);
    }
}