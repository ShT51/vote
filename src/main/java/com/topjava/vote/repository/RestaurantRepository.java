package com.topjava.vote.repository;

import com.topjava.vote.model.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    
    @Query(value = """
                   SELECT r FROM RestaurantEntity AS r
                   LEFT JOIN FETCH r.dishes
                   """)
    List<RestaurantEntity> findAllWithDishes();
    
    @Query(value = """
                   SELECT r FROM RestaurantEntity AS r
                   LEFT JOIN FETCH r.dishes
                   WHERE r.id = ?1
                   """)
    Optional<RestaurantEntity> findWithDishesById(long id);
}