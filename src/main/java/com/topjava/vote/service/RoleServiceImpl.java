package com.topjava.vote.service;

import com.topjava.vote.model.entity.RoleEntity;
import com.topjava.vote.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.topjava.vote.model.enums.ROLE.ADMIN;
import static com.topjava.vote.model.enums.ROLE.USER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    
    private final RoleRepository roleRepository;
    
    @Override
    @Transactional(readOnly = true)
    public RoleEntity getUserRole() {
        return findRoleEntityByName(USER.name());
    }
    
    @Override
    @Transactional(readOnly = true)
    public RoleEntity getAdminRole() {
        return findRoleEntityByName(ADMIN.name());
    }
    
    private RoleEntity findRoleEntityByName(String name) {
        return roleRepository.findByName("ROLE_" + name)
                             .orElseGet(() -> {
                                 log.error("Role with name: '{}' not found", name);
                                 throw new UnsupportedOperationException();
                             });
    }
}
