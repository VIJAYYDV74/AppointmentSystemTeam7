package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
