package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.ServicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private BusinessRepository businessRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServicesService.class);

    public Services createService(long businessId, Services services) {
        services.setBusiness(businessRepository.getById(businessId));
        Services services1 = servicesRepository.save(services);
        try{
            if (services1 == null) {
                throw new InternalServerException("InternalServerException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return services1;
    }

    public List<Services> getBusinessServices(long businessId) {
        List<Services> services = servicesRepository.findByBusinessBusinessid(businessId);
        return services;
    }
}
