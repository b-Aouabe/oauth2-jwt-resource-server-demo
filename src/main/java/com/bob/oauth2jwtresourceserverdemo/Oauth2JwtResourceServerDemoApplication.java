package com.bob.oauth2jwtresourceserverdemo;

//import com.bob.oauth2jwtresourceserverdemo.config.RsaKeysConfig;
//import com.bob.oauth2jwtresourceserverdemo.model.Role;
//import com.bob.oauth2jwtresourceserverdemo.model.User;
//import com.bob.oauth2jwtresourceserverdemo.service.RoleService;
//import com.bob.oauth2jwtresourceserverdemo.service.UserService;
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

    public static void main(String[] args) {
        SpringApplication.run(Oauth2JwtResourceServerDemoApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoderTools(){
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public CommandLineRunner init(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
//        return args -> {
//            if(userService.findAll().isEmpty()){
//                roleService.addRole(new Role(null, "USER"));
//                roleService.addRole(new Role(null, "ADMIN"));
//                roleService.addRole(new Role(null, "CUSTOMER_MANAGER"));
//                roleService.addRole(new Role(null, "PRODUCT_MANAGER"));
//                roleService.addRole(new Role(null, "BILLS_MANAGER"));
//
//                userService.addUser(new User(null, "bob", passwordEncoder.encode("12345"), new ArrayList<>()));
//                userService.addUser(new User(null, "admin", passwordEncoder.encode("12345"), new ArrayList<>()));
//                userService.addUser(new User(null, "lee", passwordEncoder.encode("12345"), new ArrayList<>()));
//                userService.addUser(new User(null, "mike", passwordEncoder.encode("12345"), new ArrayList<>()));
//                userService.addUser(new User(null, "sara", passwordEncoder.encode("12345"), new ArrayList<>()));
//
//                roleService.addRoleToUser("bob", "USER");
//                roleService.addRoleToUser("admin", "USER");
//                roleService.addRoleToUser("admin", "ADMIN");
//                roleService.addRoleToUser("lee", "USER");
//                roleService.addRoleToUser("lee", "CUSTOMER_MANAGER");
//                roleService.addRoleToUser("mike", "USER");
//                roleService.addRoleToUser("mike", "PRODUCT_MANAGER");
//                roleService.addRoleToUser("sara", "USER");
//                roleService.addRoleToUser("sara", "BILLS_MANAGER");
//
//            }
//        };
//    }
}
