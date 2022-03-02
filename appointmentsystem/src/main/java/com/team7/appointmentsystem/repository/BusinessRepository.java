package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Business;
import com.team7.appointmentsystem.entity.Services;
import com.team7.appointmentsystem.miscellinious.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    //admin dashboard
    //@Query(value = "select * from business ",nativeQuery = true)
    //List<Business> getAllBusiness();

    @Query(value = "select count(*) from business",nativeQuery = true)
    int countTotalBusiness();


    @Query(value = "select count(*) from business b where cast(createdtime as Date) = cast( ?1 as Date)",nativeQuery = true)
    int countBusinessesToday(LocalDateTime now);
   // @Query(value = "select businessname,servicename,serviceprice,categoryname from business where rating>3 ORDER BY rating",nativeQuery = true)
   // List<Map<String,Object>> topBusiness();

    @Query(value = "select * from business",nativeQuery = true)
    List<Business> getAllbusinesses();
    //admin dashboard


    @Query(value = "select businessname,businessdescription,businesstitle,businessnumber,businessemail,businessimages from business b where b.businessid= ?1",nativeQuery = true)
    List<Map<String, Object>> getBusiness(Long businessid);

    @Query(value = "select businessimages from business b where b.businessid= ?1",nativeQuery = true)
    String getImages(Long businessid);


    @Query(value = "select * from business b where b.businessid= ?1",nativeQuery = true)
    Business findByBusinessId(Long businessId);
    //  businessdetails

}