package com.dee.secure_api.repository;

import com.dee.secure_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // used when loading a user for login (Spring Security needs this).//
    Optional<User> findByUsername(String username);
    //if your login is email-based.//
    Optional<User> findByEmail(String email);
    //existsByUsername / existsByEmail.//
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
