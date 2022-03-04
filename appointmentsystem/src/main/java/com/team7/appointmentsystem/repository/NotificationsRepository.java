package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.NotificationsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsTable, Long> {
    List<NotificationsTable> findBySenderUserid(long userId);
}
