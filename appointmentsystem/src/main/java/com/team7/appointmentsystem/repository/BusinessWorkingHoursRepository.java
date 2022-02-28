package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.BusinessWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessWorkingHoursRepository extends JpaRepository<BusinessWorkingHours, Long> {
    BusinessWorkingHours findByBusinessHoursAndNameOfDay(long businessId, String monday);
}
