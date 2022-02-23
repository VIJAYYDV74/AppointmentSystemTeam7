package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findByUserid(long i);
}
