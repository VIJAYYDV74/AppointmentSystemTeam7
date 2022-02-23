package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.Categories;
import com.team7.appointmentsystem.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getCategories() {
        return categoriesRepository.findAll();
    }
}
