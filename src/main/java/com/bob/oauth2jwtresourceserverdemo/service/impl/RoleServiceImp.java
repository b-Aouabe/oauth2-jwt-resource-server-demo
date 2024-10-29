package com.bob.oauth2jwtresourceserverdemo.service.Impl;


import com.bob.oauth2jwtresourceserverdemo.model.Role;
import com.bob.oauth2jwtresourceserverdemo.model.User;
import com.bob.oauth2jwtresourceserverdemo.repository.RoleRepo;
import com.bob.oauth2jwtresourceserverdemo.repository.UserRepo;
import com.bob.oauth2jwtresourceserverdemo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public RoleServiceImp(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByRoleName(roleName);
        user.getRoles().add(role);
        userRepo.save(user);
    }
}
