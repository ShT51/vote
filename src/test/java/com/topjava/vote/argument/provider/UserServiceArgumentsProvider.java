package com.topjava.vote.argument.provider;

import com.topjava.vote.argument.RoleTestArgument;
import com.topjava.vote.argument.UserTestArgument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class UserServiceArgumentsProvider {
    
    public static List<Arguments> getUserEntityWithRoles() {
        return List.of(Arguments.of(UserTestArgument.getEntity()));
    }
    
    public static List<Arguments> getUserById() {
        return getUserEntityWithRoles();
    }
    
    public static List<Arguments> registerUser() {
        return List.of(Arguments.of(
                UserTestArgument.getEntity(),
                UserTestArgument.getDto(),
                RoleTestArgument.getEntity()));
    }
}
