package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.team7.appointmentsystem.miscellinious.UserDetails;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CategoriesRepository;
import com.team7.appointmentsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CategoriesRepository categoriesRepository;

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    public String registerUser(Users user) throws UserAlreadyExistsException {
        System.out.println("Register Request arrived");

            Users tempUser = userRepository.findByEmail(user.getEmail());
            try{
                if(tempUser != null) {
                    throw new UserAlreadyExistsException("Email Id Already Exists. Please use different Email Id.");
                }else{
                    user.setCreatedTime(LocalDateTime.now());
                    user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
                    Users savedUser = userRepository.save(user);
                    Users user1 = userRepository.save(user);
                    if (user1==null){

                        throw new InternalServerException("InternalServerException");
                    }
                    return "User Registered";
                }
            } catch(Exception e){
            logger.error(e.getMessage());
            return "User not created";
        }
    }

    public List<Map<String, Object>> showUsers() {
        List<Map<String, Object>> users = new ArrayList<>();
        userRepository.findAllUsers().forEach(users::add);
        return users;
    }

    public Users getuser(long userId) {
        Users user = userRepository.findByUserid(userId);
        return user;
    }
}
