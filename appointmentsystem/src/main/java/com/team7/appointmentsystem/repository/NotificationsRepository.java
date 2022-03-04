package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.NotificationsTable;
import com.team7.appointmentsystem.entity.UserNotifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<NotificationsTable, Long> {
    List<NotificationsTable> findBySenderUserid(long userId);
}
