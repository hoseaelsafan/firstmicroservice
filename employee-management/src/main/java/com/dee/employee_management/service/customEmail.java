package com.dee.employee_management.service;

import com.dee.employee_management.repository.Employeerepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class customEmail {

    private final Employeerepository employeerepository;

    public Boolean getByemail(String email){
        return employeerepository.existsByEmail(email);
    }
}
