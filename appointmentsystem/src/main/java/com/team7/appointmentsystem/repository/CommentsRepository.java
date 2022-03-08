package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findTop5ByOrderByLocalDateTimeAsc();

    @Query(value = "select * from comments c where c.commentedby= ?1 and rating>3",nativeQuery = true)
    List<Comments> getFavourites(Long userid);

    List<Comments> findByUsersUserid(Long userid);

    List<Comments> findByBusinessBusinessid(long businessId);


}
