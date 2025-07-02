package com.authservice.auth_service.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.authservice.auth_service.Repository.UserRepository;
import com.authservice.auth_service.entity.User;
import com.authservice.auth_service.model.AuthRequest;
import com.authservice.auth_service.security.JwtUtil;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    	
    	
    	
        User user = userRepository.findByUsername(request.getUsername());
System.err.println("user------------"+user.getUsername());
System.err.println("user------------"+user.getPassword());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        	System.err.println("inside userblock----------");
            String token = jwtUtil.generateToken(user);
            System.out.println("after jwt token"+token);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }
}