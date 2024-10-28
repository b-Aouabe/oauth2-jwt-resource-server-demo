package com.bob.oauth2jwtresourceserverdemo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Map<String, Object> test(Authentication authentication) {
        return Map.of(
                "message", "test",
                "username", authentication.getName(),
                "authorities", authentication.getAuthorities(),
                "password", authentication.getCredentials().toString()
        );
    }

//    @GetMapping("/profile")
//    public Map<String,Object> dataTest(Authentication authentication){
//        return Map.of("dataTest","myData",
//                "authentication",authentication.getName(),
//                "authorities",authentication.getAuthorities());
//    }
//
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getDataAdmin(){
        return "get data for admin";
    }
//
    @GetMapping("/public")
    public String getPublicData(){
        return "get data for everyone";
    }

}
