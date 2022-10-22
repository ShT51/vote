package com.topjava.vote.repository;

import com.topjava.vote.model.entity.VoteEntity;
import com.topjava.vote.model.response.RestaurantScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    
    @Query(value = """
                   SELECT new com.topjava.vote.model.response.RestaurantScore(r.id, r.name, COUNT(v))
                   FROM VoteEntity AS v
                   INNER JOIN RestaurantEntity AS r
                   ON r.id = v.restaurant.id
                   WHERE v.voteDate = current_date
                   GROUP BY v.restaurant.id
                   ORDER BY COUNT(v) DESC
                   """)
    List<RestaurantScore> findWinners();
    
    @Query(value = """
                   SELECT v FROM VoteEntity AS v
                   WHERE v.user.id = ?1
                   AND v.voteDate = current_date
                   """)
    Optional<VoteEntity> findUserVoteForToday(long userId);
}
