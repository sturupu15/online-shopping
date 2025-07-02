package com.authservice.auth_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authservice.auth_service.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
