package com.dee.employee_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class registerEmployeeRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Department is Required")
    private String department;

    @NotBlank(message = "Email is Required")
    @Email(message = "Email format invalid")
    private String email;
}
