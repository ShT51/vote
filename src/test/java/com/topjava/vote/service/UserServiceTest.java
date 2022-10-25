package com.topjava.vote.service;

import com.topjava.vote.exception.VoteException;
import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.entity.RoleEntity;
import com.topjava.vote.model.entity.UserEntity;
import com.topjava.vote.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.topjava.vote.argument.UserTestArgument.EMAIL;
import static com.topjava.vote.argument.UserTestArgument.ENCRYPTED_PASSWORD;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.UserServiceArgumentsProvider#getUserEntityWithRoles")
    void getUserEntityWithRoles(UserEntity expected) {
        when(userRepository.findByEmailWithRoles(anyString())).thenReturn(of(expected));
        assertEquals(expected, userService.getUserEntityWithRoles(EMAIL));
    }
    
    @Test
    void getUserEntityWithRoles_fail() {
        when(userRepository.findByEmailWithRoles(anyString())).thenReturn(empty());
        assertThrows(VoteException.class, () -> userService.getUserEntityWithRoles(EMAIL));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.UserServiceArgumentsProvider#getUserById")
    void getUserById(UserEntity expected) {
        when(userRepository.findById(anyLong())).thenReturn(of(expected));
        assertEquals(expected, userService.getUserById(1L));
    }
    
    @Test
    void getUserById_fail() {
        when(userRepository.findById(anyLong())).thenReturn(empty());
        assertThrows(VoteException.class, () -> userService.getUserById(1L));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.UserServiceArgumentsProvider#registerUser")
    void registerUser_fail(UserEntity userEntity, UserDto userDto) {
        when(userRepository.findByEmailWithRoles(anyString())).thenReturn(of(userEntity));
        assertThrows(VoteException.class, () -> userService.registerUser(userDto));
    }
    
    @ParameterizedTest
    @MethodSource("com.topjava.vote.argument.provider.UserServiceArgumentsProvider#registerUser")
    void registerUser(UserEntity userEntity, UserDto userDto, RoleEntity userRoleEntity) {
        when(userRepository.findByEmailWithRoles(anyString())).thenReturn(empty());
        when(roleService.getUserRole()).thenReturn(userRoleEntity);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(ENCRYPTED_PASSWORD);
        when(userRepository.save(any())).thenReturn(userEntity);
        assertDoesNotThrow(() -> userService.registerUser(userDto));
    }
}