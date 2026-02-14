package com.dee.employee_management.repository;

import com.dee.employee_management.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employeerepository extends JpaRepository<employee, Long> {
    Boolean existsByEmail(String email);
}
