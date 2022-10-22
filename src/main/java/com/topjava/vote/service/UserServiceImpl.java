package com.topjava.vote.service;

import com.topjava.vote.model.dto.UserDto;
import com.topjava.vote.model.entity.UserEntity;
import com.topjava.vote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserEntityWithRoles(String email) {
        return userRepository.findByEmailWithRoles(email).orElseGet(() -> {
            log.error("User with email: '{}' not found", email);
            throw new UnsupportedOperationException();
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserById(long id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseGet(() -> {
            log.error("User with id: '{}' not found", id);
            throw new UnsupportedOperationException();
        });
    }
    
    @Override
    public void registerUser(UserDto userDto) {
        userRepository.findByEmailWithRoles(userDto.getEmail().toLowerCase())
                      .ifPresentOrElse(userEntity -> {
                                  log.error("User with email: '{}' is already exists", userEntity.getEmail());
                                  throw new UnsupportedOperationException();
                              },
                              () -> {
                                  UserEntity newUser = UserDto.toEntity(userDto);
                                  newUser.setRoles(new ArrayList<>(List.of(roleService.getUserRole())));
                                  newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
                                  userRepository.save(newUser);
                              });
    }
}