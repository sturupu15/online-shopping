package com.authservice.auth_service.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.authservice.auth_service.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	//@Value("${jwt.secret}")
    private final String secret = "secret-key";

   // @Value("${jwt.expiration}")
    private final long expiration = 86400000; // 1 day

    public String generateToken(User username) {
    	System.out.println("jwt---------");
    	Map<String, Object> customClaims = new HashMap<>();

        customClaims.put("username", username.getUsername());

        customClaims.put("password", username.getPassword());
        return Jwts.builder()
        		.setClaims(customClaims)
                .setSubject(username.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}