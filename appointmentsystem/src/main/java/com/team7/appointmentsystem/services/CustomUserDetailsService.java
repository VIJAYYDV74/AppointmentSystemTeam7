package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.configuration.CustomUserDetails;
import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Fetch UserDetails From Database
        Users user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
