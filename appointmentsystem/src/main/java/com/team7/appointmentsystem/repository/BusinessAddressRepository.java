package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.BusinessAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAddressRepository extends JpaRepository<BusinessAddress, Long> {
}
