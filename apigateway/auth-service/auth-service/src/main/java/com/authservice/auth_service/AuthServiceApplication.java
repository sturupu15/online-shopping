package com.authservice.auth_service;

import org.springframework.boot.SpringApplication;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

 

import java.security.Key;

import java.util.Date;

import java.util.HashMap;

import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.authservice.auth_service.Repository.UserRepository;
import com.authservice.auth_service.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
      /*  AuthServiceApplication generator = new AuthServiceApplication();

        

        Map<String, Object> customClaims = new HashMap<>();

        customClaims.put("role", "admin");

        customClaims.put("userId", 123);

 

        String token = generator.generateToken("testUser", customClaims, 3600000); // 1 hour expiration

        System.out.println("Generated JWT: " + token);*/
    }

    @Override
    public void run(String... args) throws Exception {
        // insert test user
        userRepository.save(new User("nithya", "123456"));
    }
   /* public String generateToken(String subject, Map<String, Object> claims, long expirationMillis) {

        // Generate a secure random key for signing (for HS256)

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Or load a private key for RSA256

 

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        Date expiration = new Date(nowMillis + expirationMillis);

 

        return Jwts.builder()

                .setClaims(claims) // Custom claims

                .setSubject(subject) // Subject of the token (e.g., user ID)

                .setIssuedAt(now) // When the token was issued

                .setExpiration(expiration) // When the token expires

                .signWith(key, SignatureAlgorithm.HS256) // Sign with the key and algorithm

                .compact(); // Build and compact the JWT into a string

    }*/
}