package com.bob.oauth2jwtresourceserverdemo.service.Impl;

//import com.bob.jwtdemo.model.User;
import com.bob.oauth2jwtresourceserverdemo.model.User;
import com.bob.oauth2jwtresourceserverdemo.repository.UserRepo;
import com.bob.oauth2jwtresourceserverdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user){
        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
