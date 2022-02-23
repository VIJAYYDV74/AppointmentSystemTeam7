package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Visiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitingRepository extends JpaRepository<Visiting, Long> {
}
