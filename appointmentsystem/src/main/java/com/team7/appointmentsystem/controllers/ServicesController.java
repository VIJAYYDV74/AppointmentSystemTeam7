package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.services.BusinessService;
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

    @Autowired
    private BusinessService businessService;

    @Transactional
    @PostMapping("/user/business/{businessId}/createService/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Services> createService(@RequestBody Services services, @PathVariable long businessId){
        Services services1 = servicesService.createService(businessId, services);
        return ResponseEntity.ok(services1);
    }

    @GetMapping("/user/business/{businessId}/getServices")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Services>> getBusinessServices(long businessId){
        return ResponseEntity.ok(servicesService.getBusinessServices(businessId));
    }

    @DeleteMapping(value = "/user/business/{businessId}/deleteService", params = "serviceId")
    public ResponseEntity<StrObject> deleteService(@PathVariable Long businessId, @RequestParam Long serviceId) {
        StrObject service = businessService.deleteService(businessId, serviceId);
        return ResponseEntity.ok(service);
    }

    @PutMapping(value = "/user/business/{businessId}/updateService", params = "serviceId")
    public ResponseEntity<StrObject> updateService(@PathVariable Long businessId, @RequestParam
            Long serviceId, @RequestBody Services updateService) {
        return ResponseEntity.ok(businessService.updateService(businessId, serviceId, updateService));
    }

}
