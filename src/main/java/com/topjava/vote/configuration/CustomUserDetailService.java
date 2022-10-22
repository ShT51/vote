package com.topjava.vote.configuration;

import com.topjava.vote.model.AuthenticatedUser;
import com.topjava.vote.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    
    private final UserService userService;
    
    @Override
    @Transactional(readOnly = true)
    public AuthenticatedUser loadUserByUsername(String email) {
        return new AuthenticatedUser(userService.getUserEntityWithRoles(email));
    }
}
