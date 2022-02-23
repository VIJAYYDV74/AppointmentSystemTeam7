package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String changePassword(String oldPassword, String newPassword, String emailID) {

        Users userOptional = userRepo.findByEmail(emailID);
        try {
            if (userOptional != null) {
                Users user = userOptional;
                System.out.println(oldPassword + ": is the raw password");

                if (passwordEncoder.matches(oldPassword, user.getUserPassword())) {
                    user.setUserPassword(passwordEncoder.encode(newPassword));
                    System.out.println(newPassword + ": Password Changed Successfully");
                    userRepo.save(user);
                } else {
                    System.out.println("Password do not match");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Changed Password Successfully";
    }
}
