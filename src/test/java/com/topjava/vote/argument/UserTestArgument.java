package com.topjava.vote.argument;

import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.entity.UserEntity;

public class UserTestArgument {
    
    public static final String EMAIL = "email@email.ru";
    public static final String ENCRYPTED_PASSWORD = "encryptedPass";
    
    public static UserDto getDto() {
        return UserDto.builder()
                      .name("testUser")
                      .email(EMAIL)
                      .password("password")
                      .build();
        
    }
    
    public static UserEntity getEntity() {
        return UserEntity.builder()
                         .id(1L)
                         .name("testUser")
                         .email(EMAIL)
                         .password(ENCRYPTED_PASSWORD)
                         .build();
        
    }
}
