package com.bob.oauth2jwtresourceserverdemo.repository;

import com.bob.oauth2jwtresourceserverdemo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
