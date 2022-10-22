package com.topjava.vote.service;

import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    
    @Transactional(readOnly = true)
    UserEntity getUserEntityWithRoles(String email);
    
    @Transactional(readOnly = true)
    UserEntity getUserById(long id) throws UsernameNotFoundException;
    
    void registerUser(UserDto userDto);
}
