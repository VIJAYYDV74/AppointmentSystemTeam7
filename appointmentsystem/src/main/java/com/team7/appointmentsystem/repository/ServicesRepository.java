package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ServicesRepository extends JpaRepository<Services, Long> {

    Services findByServiceidAndBusinessBusinessid(long serviceid, long businessid);

    List<Services> findByBusinessBusinessid(long businessId);



    //user_dashboard

    @Query(value = "select * from services s ORDER BY servicename " ,nativeQuery = true)
    List<Map<String,Object>> getByServiceNameOrderByServicePriceAsc();


    //user_dashboard


    //business details
    @Query(value = "select servicename,serviceprice,servicedesc from services s where s.businessid= ?1",nativeQuery = true)
    List<Map<String,Object>> getPricing(Long businessid);

    //admin business
   // @Query(value = "select servicename,serviceprice from services s where s.businessid= ?1",nativeQuery = true)
    //Map<String, Object> getbusinessDetails(Long id);


}
