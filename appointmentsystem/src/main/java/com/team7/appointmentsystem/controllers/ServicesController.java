package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @Transactional
    @PostMapping("business/{businessId}/createService/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Services> createService(@RequestBody Services services, @PathVariable long businessId){
        Services services1 = servicesService.createService(businessId, services);
        return ResponseEntity.ok(services1);
    }

    @GetMapping("business/{businessId}/getServices")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Services>> getBusinessServices(long businessId){
        return ResponseEntity.ok(servicesService.getBusinessServices(businessId));
    }

}
