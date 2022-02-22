package com.team7.appointmentsystem.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH )
    public String myError() {
        return "<center>" +
                "<h1>Something went wrong</h1>" +
                "<p>Error 404. Page not found. Please check the URL.</p>" +
                "</center>";
    }

}
