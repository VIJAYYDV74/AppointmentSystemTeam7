package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.exceptions.PasswordsDoNotMatchException;
import com.team7.appointmentsystem.models.PasswordObject;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public PasswordObject changePassword(String oldPassword, String newPassword, Long userId) {

        Users userOptional = userRepo.findByUserid(userId);
        PasswordObject dummy = new PasswordObject();
        try {
            if (userOptional != null) {
                Users user = userOptional;

                System.out.println(oldPassword + ": is the raw password");
                if (passwordEncoder.matches(oldPassword, user.getUserPassword())) {
                    dummy.setOldPassword(user.getUserPassword());
                    user.setUserPassword(passwordEncoder.encode(newPassword));
                    dummy.setNewPassword(user.getUserPassword());
                    System.out.println(newPassword + ": Password Changed Successfully");
                    userRepo.save(user);

                } else {
                    throw new PasswordsDoNotMatchException("Password do not match");
                }
            }
        } catch (Exception e) {
//            throw new Exception("Couldn't Change Password");
            e.printStackTrace();
        }finally {
            return dummy;
        }
    }

    public Users getUser(long UserId) {
        Users user = userRepo.findByUserid(UserId);
        return user;
    }


}
