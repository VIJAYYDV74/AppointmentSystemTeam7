package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @Transactional
    @PostMapping("business/{businessId}/createService/")
    public void createService(@RequestBody Services services, @PathVariable long businessId){
        System.out.println("Request Arrived");
        servicesService.createService(businessId, services);
    }

    @GetMapping("business/{businessId}/getServices")
    public List<Services> getBusinessServices(long businessId){
        return servicesService.getBusinessServices(businessId);
    }

}
