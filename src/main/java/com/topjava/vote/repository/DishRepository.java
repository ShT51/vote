package com.topjava.vote.repository;

import com.topjava.vote.model.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
    
    @Query("""
           UPDATE DishEntity AS d SET d.available = false
           WHERE d.id = ?1
           """)
    @Modifying
    void softDelete(long id);
    
    @Query("""
           SELECT d.id FROM DishEntity AS d
           WHERE d.id = ?1
           """)
    Optional<Long> checkId(long id);
}