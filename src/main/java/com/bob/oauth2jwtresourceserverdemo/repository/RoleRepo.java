package com.bob.oauth2jwtresourceserverdemo.repository;

import com.bob.oauth2jwtresourceserverdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
