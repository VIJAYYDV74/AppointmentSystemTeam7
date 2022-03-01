package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Users;
import com.team7.appointmentsystem.miscellinious.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select userid,firstname,lastname,email,createdtime from users",nativeQuery = true)
    List<Map<String, Object>> findAllUsers();

    Users findByEmail(String email);

    Users findByUserid(long id);
}
