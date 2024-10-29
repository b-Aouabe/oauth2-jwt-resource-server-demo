package com.bob.oauth2jwtresourceserverdemo.service.impl;

import com.bob.oauth2jwtresourceserverdemo.model.AppUser;
import com.bob.oauth2jwtresourceserverdemo.model.Role;
import com.bob.oauth2jwtresourceserverdemo.service.AppUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserService appUserService;

    public CustomUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.loadUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        String[] roles = appUser.getRoles().stream().map(Role::getRoleName).toArray(String[]::new);
        UserDetails principal = User.withUsername(username).password(appUser.getPassword()).roles(roles).build();
        System.out.printf("principal: %s ", principal);
        return principal;
    }
}
