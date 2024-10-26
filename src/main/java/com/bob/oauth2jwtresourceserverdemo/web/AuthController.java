package com.bob.oauth2jwtresourceserverdemo.web;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

//    @PostMapping("/get-token")
//    public Map<String, String> getToken(Authentication authentication) {  note: we used this when we hadn't yet create the Bean "AuthenticationManager"
//        Map<String, String> tokens = new HashMap<>();
//        Instant instant = Instant.now();
//        String scope = authentication.getAuthorities()
//                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
//                .subject(authentication.getName())
//                .issuedAt(instant)
//                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
//                .issuer("security-service")
//                .claim("scope", scope)
//                .build();
//        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
//        tokens.put("access_token", accessToken);
//        return tokens;
//}
//    @PostMapping("/get-token")
//    public Map<String, String> getToken(String username, String password) { // note: now that we created a Bean for both "UserDetailsService" and "AuthenticationManager" we can directly receive the username and the password from the user
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        Map<String, String> tokens = new HashMap<>();
//        Instant instant = Instant.now();
//        String scope = authentication.getAuthorities()
//                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
//                .subject(authentication.getName())
//                .issuedAt(instant)
//                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
//                .issuer("security-service")
//                .claim("scope", scope)
//                .build();
//        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
//        tokens.put("access_token", accessToken);
//        return tokens;
//    }

    @PostMapping("/get-token")
    public Map<String, String> getTokens(
            String grantType,
            String username,
            String password,
            boolean withRefreshToken,
            String refreshToken
            )
    {// note: now that we created a Bean for both "UserDetailsService" and "AuthenticationManager" we can directly receive the username and the password from the user
        String subject = null;
        String scope = null;
        Map<String, String> tokens = new HashMap<>();
        Instant instant = Instant.now();

        if(grantType.equals("password")){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        }
        if(grantType.equals("refresh_token")){
            Jwt jwt = jwtDecoder.decode(refreshToken);
            subject = jwt.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities
                    .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        }

        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?5:30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
        tokens.put("access_token", accessToken);

        //the refresh token
        if (withRefreshToken) {
            JwtClaimsSet jwtRefreshClaims = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();
            String genRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaims)).getTokenValue();
            tokens.put("refresh_token", genRefreshToken);
        }
        return tokens;
    }

//    @PostMapping("/get-token")
//    public Map<String, String> getTokens(
//            String username,
//            String password,
//            boolean withRefreshToken
//    )
//    {// note: now that we created a Bean for both "UserDetailsService" and "AuthenticationManager" we can directly receive the username and the password from the user
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        Map<String, String> tokens = new HashMap<>();
//        Instant instant = Instant.now();
//        String scope = authentication.getAuthorities()
//                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//
//
//        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
//                .subject(authentication.getName())
//                .issuedAt(instant)
//                .expiresAt(instant.plus(withRefreshToken?5:30, ChronoUnit.MINUTES))
//                .issuer("security-service")
//                .claim("scope", scope)
//                .build();
//        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
//        tokens.put("access_token", accessToken);
//
//        //the refresh token
//        if (withRefreshToken) {
//            JwtClaimsSet jwtRefreshClaims = JwtClaimsSet.builder()
//                    .subject(authentication.getName())
//                    .issuedAt(instant)
//                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
//                    .issuer("security-service")
//                    .build();
//            String genRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaims)).getTokenValue();
//            tokens.put("refresh_token", genRefreshToken);
//        }
//        return tokens;
//    }

    @PostMapping("/refresh-token")
    public Map<String, String> refreshToken(String refreshToken) {
        Instant instant = Instant.now();
        Jwt jwt = jwtDecoder.decode(refreshToken);
        String subject = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String scope = authorities
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        Map<String, String> token = new HashMap<>();
        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
        token.put("access_token", accessToken);
        return token;
    }

}
