package com.dee.employee_management.service;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.EmployeePayload;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;

import java.util.List;

public interface EmployeeServiceInterface {
//    EmployeeManagementResponse<employee> saveEmployee(registerEmployeeRequest request);
    EmployeeManagementResponse<EmployeePayload> saveEmployee(registerEmployeeRequest request);
    EmployeeManagementResponse<List<EmployeePayload>> getAllEmployees();
    EmployeeManagementResponse<EmployeePayload> getEmployeeById(Long id);
    EmployeeManagementResponse<EmployeePayload> updateEmployee(registerEmployeeRequest request, Long id);
    EmployeeManagementResponse<String> deleteEmployee(Long id);
}
