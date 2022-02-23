package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.NotificationTypes;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.NotificationTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypesService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationTypesService.class);

    public List<NotificationTypes> getNotificationTypes(){
        return notificationTypeRepository.findAll();
    }

    public void addNotificationType(NotificationTypes notificationTypes){
        try {
            NotificationTypes notificationTypes1 = notificationTypeRepository.save(notificationTypes);
            if (notificationTypes1==null){
                throw new InternalServerException("InternalServerException");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
