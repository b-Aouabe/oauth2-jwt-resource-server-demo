package com.bob.oauth2jwtresourceserverdemo.repository;

import com.bob.oauth2jwtresourceserverdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
