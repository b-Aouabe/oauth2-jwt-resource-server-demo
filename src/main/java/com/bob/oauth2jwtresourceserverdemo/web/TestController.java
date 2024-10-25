package com.bob.oauth2jwtresourceserverdemo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/profile")
    public Map<String,Object> dataTest(Authentication authentication){
        return Map.of("dataTest","myData",
                "authentication",authentication.getName(),
                "authorities",authentication.getAuthorities());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String getDataAdmin(){
        return "get data for admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String getDataUser(){
        return "get data for user";
    }

}
