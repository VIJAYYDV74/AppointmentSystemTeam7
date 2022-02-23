package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Visiting;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.VisitingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VisitingService {

    @Autowired
    private VisitingRepository visitingRepository;

    private static final Logger logger = LoggerFactory.getLogger(VisitingService.class);

    public void businessVisited(Visiting visiting){
        try{
            visiting.setLocalDateTime(LocalDateTime.now());
            Visiting visiting1 = visitingRepository.save(visiting);
            if (visiting1 == null) {
                throw new InternalServerException("InternalServerException");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}