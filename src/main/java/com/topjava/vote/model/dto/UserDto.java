package com.topjava.vote.model.dto;

import com.topjava.vote.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private String name;
    private String email;
    private String password;
    
    public static UserEntity toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return UserEntity.builder()
                         .name(dto.getName())
                         .email(dto.getEmail())
                         .build();
    }
}
