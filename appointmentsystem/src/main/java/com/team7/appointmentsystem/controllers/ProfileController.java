package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.models.PasswordObject;
import com.team7.appointmentsystem.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping("/profile/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordObject object) {
        String result = profileService.changePassword(object.getOldPassword(), object.getNewPassword(), object.getEmailID());
        return ResponseEntity.ok(result);
    }
}
