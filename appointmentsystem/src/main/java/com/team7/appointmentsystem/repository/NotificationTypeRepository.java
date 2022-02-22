package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.NotificationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationTypes, Integer> {
}
