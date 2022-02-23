package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.GenderCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderCategoryRepository extends JpaRepository<GenderCategories, Integer> {
}
