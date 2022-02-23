package com.team7.appointmentsystem.controllers;


import com.team7.appointmentsystem.entity.Categories;
import com.team7.appointmentsystem.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/getCategories")
    public List<Categories> getCategories(){
        return categoriesService.getCategories();
    }

}
