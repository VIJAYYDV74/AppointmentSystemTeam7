package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.team7.appointmentsystem.miscellinious.UserDetails;
import com.team7.appointmentsystem.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RegisterUser {

    @Autowired
    private RegisterService registerService;


    @GetMapping("/showUsers")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Map<String, Object>> showUsers(){
        return registerService.showUsers();
    }

    @GetMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String signUpForm() {
        return "SignupForm";
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> registerUser(@RequestBody Users user) throws UserAlreadyExistsException {
        System.out.println(user);
        String msg = registerService.registerUser(user);
        return ResponseEntity.ok(msg);
    }
}