package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {


    // admin dashboard
    //@Query(value = "select * from payments ",nativeQuery = true)
    //List<Payments> getAllPayments();

    @Query(value = "select sum(amount) as totalRevenue from payments",nativeQuery = true)
    int countTotalRevenue();

    @Query(value = "select sum(amount) as lastWeekRevenue from payments where cast(paymentdate as DATE) BETWEEN cast( ?1 as Date) and cast( ?2 as Date) ",nativeQuery = true)
    int countRevenueThisWeek(LocalDateTime before,LocalDateTime now);

    // admin dashboard

}
