package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.LikesRepository;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;


}
