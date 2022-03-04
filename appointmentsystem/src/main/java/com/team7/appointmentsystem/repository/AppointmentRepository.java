package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByBusinessBusinessid(long businessId);

    List<Appointment> findByUsersUserid(long userId);



    //user_dashboard

   // @Query(value = "select * from appointments a where a.userid= ?1",nativeQuery = true)
   // List<Map<String,Object>> TotalAppointmentByUserid(Long userId);

    @Query(value = "select * from appointments a where a.userid= ?1 and cast(a.bookeddate as Date) > cast( ?2 as Date)",nativeQuery = true)
    List<Appointment> getUpcomingAppointments(Long userId , LocalDateTime now);

    // admin dashboard

    @Query(value = "select * from appointments",nativeQuery = true)
    List<Appointment> getAllAppointments();

   // @Query(value = "select count(*) from appointments a where a.userid= ?1 ",nativeQuery = true)
    //int countTotalAppointmentByUserid(Long userid);

   // @Query(value = "select count(*) from appointments a where a.userid= ?1 and cast(a.bookeddate as Date) > cast( ?2 as Date)",nativeQuery = true)
   // int countUpcomingAppointments(Long userid, LocalDateTime now);

    @Query(value = "select count(*) from appointments a where cast(a.dateofappointment as Date) = cast( ?1 as Date)",nativeQuery = true)
    int countBookingsToday(LocalDateTime now);


    //admin dashboard


    //user_dashboard


}
