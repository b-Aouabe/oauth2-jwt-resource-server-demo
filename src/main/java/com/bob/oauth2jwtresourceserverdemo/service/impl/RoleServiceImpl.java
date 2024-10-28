package com.bob.oauth2jwtresourceserverdemo.service.impl;

import com.bob.oauth2jwtresourceserverdemo.model.Role;
import com.bob.oauth2jwtresourceserverdemo.repository.RoleRepo;
import com.bob.oauth2jwtresourceserverdemo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role addRole(String roleName) {
        Role role = new Role(null, roleName);
        return roleRepo.save(role);
    }
}
