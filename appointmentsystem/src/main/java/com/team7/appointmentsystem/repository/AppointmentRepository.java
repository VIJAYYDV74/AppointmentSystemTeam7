package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByBusinessBusinessid(long businessId);

    List<Appointment> findByUsersUserid(long userId);
}
