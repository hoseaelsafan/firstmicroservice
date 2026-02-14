package com.dee.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmployeeManagementResponse<T> {
    private String responseCode;
    private String message;
    private T payload;
}
