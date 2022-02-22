package com.team7.appointmentsystem.repository;

import com.team7.appointmentsystem.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // List<Long> findDistinctBusiness();

    Long countTotalLikesByBusinessBusinessid(long businessid);

}
