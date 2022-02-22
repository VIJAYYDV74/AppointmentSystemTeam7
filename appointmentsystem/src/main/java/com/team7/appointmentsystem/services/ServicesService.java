package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public void createService(long businessId, Services services) {
        services.setBusiness(businessRepository.getById(businessId));
        servicesRepository.save(services);
    }

    public List<Services> getBusinessServices(long businessId) {
        return servicesRepository.findByBusinessBusinessid(businessId);
    }
}
