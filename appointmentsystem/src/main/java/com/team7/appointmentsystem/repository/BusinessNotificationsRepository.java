package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.BusinessNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessNotificationsRepository extends JpaRepository<BusinessNotifications, Long> {
}
