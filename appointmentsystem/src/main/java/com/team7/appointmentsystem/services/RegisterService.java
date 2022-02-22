package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.team7.appointmentsystem.repository.BusinessRepository;
import com.team7.appointmentsystem.repository.CategoriesRepository;
import com.team7.appointmentsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    public String registerUser(Users user) {
        System.out.println("Register Request arrived");
        try{
            Users flag = userRepository.findByEmail(user.getEmail());
            if(flag == null){
                System.out.println("User with the email id does not exists");
                user.setCreatedTime(LocalDateTime.now());
                Users user1 = userRepository.save(user);
                if (user1==null){
                    throw new InternalServerException("internalServerException");
                }
                return "User Registered";
            }
            else{
                throw new UserAlreadyExistsException("Email Id Exists. Please use a different email id or login!!!");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
            return "User not created";
        }
    }

    public List<Users> showUsers() {
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Users getuser(long userId) {
        Users user = userRepository.findByUserid(userId);
        return user;
    }
}
