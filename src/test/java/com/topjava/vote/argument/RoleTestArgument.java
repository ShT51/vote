package com.topjava.vote.argument;

import com.topjava.vote.model.entity.RoleEntity;

public class RoleTestArgument {
    
    public static RoleEntity getEntity() {
        return RoleEntity.builder()
                         .id(1L)
                         .name("ROLE_USER")
                         .build();
        
    }
}
