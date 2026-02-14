package com.dee.employee_management.controller;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.EmployeePayload;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.service.EmployeeServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeManagementResponse<?>> register(@Valid @RequestBody registerEmployeeRequest request) {
        EmployeeManagementResponse<?> response = employeeServiceInterface.saveEmployee(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<EmployeeManagementResponse<List<EmployeePayload>>> getAll() {
        return ResponseEntity.ok(employeeServiceInterface.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<EmployeePayload>> getByID(@PathVariable Long id){
        EmployeeManagementResponse<EmployeePayload> response = employeeServiceInterface.getEmployeeById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<EmployeePayload>> updateById(@Valid @RequestBody registerEmployeeRequest request,@PathVariable Long id){
        EmployeeManagementResponse<EmployeePayload> response = employeeServiceInterface.updateEmployee(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(employeeServiceInterface.deleteEmployee(id));
    }

}
