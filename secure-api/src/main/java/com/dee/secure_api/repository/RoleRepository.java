package com.dee.secure_api.repository;

import com.dee.secure_api.entity.ERole;
import com.dee.secure_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
