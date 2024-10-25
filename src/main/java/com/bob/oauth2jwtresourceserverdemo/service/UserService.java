package com.bob.oauth2jwtresourceserverdemo.service;


import com.bob.oauth2jwtresourceserverdemo.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User findByUsername(String username);
    List<User> findAll();
}
