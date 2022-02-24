package com.team7.appointmentsystem.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path profileImgDir = Paths.get("./profile-image");
        String profileImgUploadPath = profileImgDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/profile-image/**").addResourceLocations("file:/" +profileImgUploadPath+"/");
    }
}
