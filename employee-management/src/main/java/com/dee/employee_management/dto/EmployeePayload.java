package com.dee.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeePayload {
    private Long id;
    private String name;
    private String department;
    private String email;
    private LocalDateTime crtdate;
}
