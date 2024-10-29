package com.bob.oauth2jwtresourceserverdemo;

//import com.bob.oauth2jwtresourceserverdemo.config.RsaKeysConfig;
//import com.bob.oauth2jwtresourceserverdemo.model.Role;
//import com.bob.oauth2jwtresourceserverdemo.model.User;
import com.bob.oauth2jwtresourceserverdemo.service.RoleService;
import com.bob.oauth2jwtresourceserverdemo.service.AppUserService;
import com.bob.oauth2jwtresourceserverdemo.config.RsaKeysConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class Oauth2JwtResourceServerDemoApplication {

//    private final RoleService roleService;
//    private final AppUserService appUserService;
//
//    public Oauth2JwtResourceServerDemoApplication(RoleService roleService, AppUserService appUserService) {
//        this.roleService = roleService;
//        this.appUserService = appUserService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtResourceServerDemoApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//
    @Bean
    public CommandLineRunner init(AppUserService userService, RoleService roleService) {
        return args -> {
            if(userService.getAllAppUsers().isEmpty()){
                roleService.addRole("USER");
                roleService.addRole("ADMIN");
                roleService.addRole("CUSTOMER_MANAGER");
                roleService.addRole("PRODUCT_MANAGER");
                roleService.addRole("BILLS_MANAGER");

                userService.addUser("bob", "aa@example.com", "12345");
                userService.addUser("admin", "bb@example.com", "12345");
                userService.addUser("lee", "cc@example.com", "12345");
                userService.addUser("mike", "dd@example.com", "12345");
                userService.addUser("sara", "ee@example.com", "12345");

                userService.addRoleToUser("bob", "USER");
                userService.addRoleToUser("admin", "USER");
                userService.addRoleToUser("admin", "ADMIN");
                userService.addRoleToUser("lee", "USER");
                userService.addRoleToUser("lee", "CUSTOMER_MANAGER");
                userService.addRoleToUser("mike", "USER");
                userService.addRoleToUser("mike", "PRODUCT_MANAGER");
                userService.addRoleToUser("sara", "USER");
                userService.addRoleToUser("sara", "BILLS_MANAGER");

            }
        };
    }
}
