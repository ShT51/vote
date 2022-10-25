package com.topjava.vote.service;

import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    
    UserEntity getUserEntityWithRoles(String email);
    UserEntity getUserById(long id) throws UsernameNotFoundException;
    void registerUser(UserDto userDto);
}
