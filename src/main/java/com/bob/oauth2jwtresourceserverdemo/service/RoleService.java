package com.bob.oauth2jwtresourceserverdemo.service;


import com.bob.oauth2jwtresourceserverdemo.model.Role;

public interface RoleService {
    Role addRole(Role role);
    void addRoleToUser(String username, String roleName);
}
