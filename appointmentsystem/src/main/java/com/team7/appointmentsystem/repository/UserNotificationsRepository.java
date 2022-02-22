package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.UserNotifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationsRepository extends JpaRepository<UserNotifications, Long> {
    List<UserNotifications> findByUsersUserid(long userId);

    List<UserNotifications> findByBusinessBusinessid(long businessId);
}
