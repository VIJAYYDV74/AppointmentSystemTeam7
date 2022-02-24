package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.models.PasswordObject;
import com.team7.appointmentsystem.repository.UserRepository;
import com.team7.appointmentsystem.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProfileService profileService;

    @RequestMapping("/profile/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordObject object) {
        String result = profileService.changePassword(object.getOldPassword(), object.getNewPassword(), object.getEmailID());
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/profile/save/{userId}")
    public ResponseEntity<Users> saveProfile(@RequestParam("profileImg") MultipartFile multipartFile,
                                             @PathVariable long userId) throws IOException
    {
        String profileImg = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Users user = userRepo.findByUserid(userId);
        user.setProfileImage(profileImg);
        Users savedUser = userRepo.save(user);
        String uploadDir = "./profile-image/" + savedUser.getUserid();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(profileImg);
            System.out.println(filePath.toFile().getAbsolutePath().toString()) ;
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            throw  new IOException("Could not save uploaded file:" + profileImg);
        }
        return ResponseEntity.ok(savedUser);
    }
}
