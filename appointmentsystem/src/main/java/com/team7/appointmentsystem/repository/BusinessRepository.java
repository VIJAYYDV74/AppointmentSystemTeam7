package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.miscellinious.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByUsersUserid(long userid);

    List<Business> findByCategoriesCategoryName(String categoryName);

    int countTotalBusinessByCategoriesCategoryName(String category);

    Business findByBusinessEmail(String businessEmail);

    List<BusinessDetails> findTop3ByCategoriesCategoryNameOrderByCreatedTimeDesc(String categoryName);

    @Query(value = "select * from business b left join services s on b.businessid = s.businessid;",
            nativeQuery = true)
    List<Business> businessServiceJoin();

    BusinessDetails findByBusinessid(long businessName);

    @Query(value = "select * from business b", nativeQuery = true)
    List<BusinessDetails> findAllBusinesses();

}