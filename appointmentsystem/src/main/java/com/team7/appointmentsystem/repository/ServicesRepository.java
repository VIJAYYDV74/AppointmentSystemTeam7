package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {

    Services findByServiceidAndBusinessBusinessid(long serviceid, long businessid);

    List<Services> findByBusinessBusinessid(long businessId);
}
