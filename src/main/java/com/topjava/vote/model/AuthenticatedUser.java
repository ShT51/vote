package com.topjava.vote.model;

import com.topjava.vote.model.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class AuthenticatedUser extends User {
    
    private final long userId;
    
    public AuthenticatedUser(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), userEntity.getAuthorities());
        this.userId = userEntity.getId();
    }
}