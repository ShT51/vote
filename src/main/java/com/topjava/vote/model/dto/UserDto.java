package com.topjava.vote.model.dto;

import com.topjava.vote.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
   
    @NotBlank
    private String name;
    
    @Email
    @NotBlank
    private String email;
    
    @Size(min = 4, max = 20)
    @NotBlank
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
