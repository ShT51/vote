package com.topjava.vote.repository;

import com.topjava.vote.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    @Query(value = """
                   SELECT u FROM UserEntity u
                   LEFT JOIN FETCH u.roles
                   WHERE u.email = ?1
                   """)
    Optional<UserEntity> findByEmailWithRoles(String email);
}
