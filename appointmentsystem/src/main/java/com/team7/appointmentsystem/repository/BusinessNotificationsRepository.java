package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.BusinessNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessNotificationsRepository extends JpaRepository<BusinessNotifications, Long> {

    List<BusinessNotifications> findByBusinessBusinessid(long businessId);

}
