package com.dee.employee_management.service;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.EmployeePayload;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.mapper.EmployeeMapper;
import com.dee.employee_management.repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//this class for service implementation(logic)
@Service
public class Employeeservice implements EmployeeServiceInterface{

    private final Employeerepository employeerepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public Employeeservice(Employeerepository employeerepository, EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
        this.employeerepository = employeerepository;
    }

    @Override
    public EmployeeManagementResponse<EmployeePayload> saveEmployee(registerEmployeeRequest request) {
        employee entity = employeeMapper.toEntity(request);// maping from dto to entity use mapper
        employee saved = employeerepository.save(entity); // save mapping to repository
        EmployeePayload UserData = employeeMapper.toLoadResponse(saved);

        return new EmployeeManagementResponse<>("00","Succes",UserData);
    }

    @Override
    public EmployeeManagementResponse<List<EmployeePayload>> getAllEmployees() {
        List<employee> list = employeerepository.findAll();
        List<EmployeePayload> FetchUser = employeeMapper.toListLoadResponse(list);
        return new EmployeeManagementResponse<>("00","Succes", FetchUser);
    }

    @Override
    public EmployeeManagementResponse<EmployeePayload> getEmployeeById(Long id) {
        Optional<employee> optional = employeerepository.findById(id);
        EmployeePayload UserData = optional.map(employeeMapper::toLoadResponse).
                orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found."));
        return new EmployeeManagementResponse<>("00", "Succes", UserData);
    }

    @Override
    public EmployeeManagementResponse<EmployeePayload> updateEmployee(registerEmployeeRequest request, Long id){
        Optional<employee> optional = employeerepository.findById(id);

        if (optional.isEmpty()) {
            return new EmployeeManagementResponse<>("01", "Employee not found", null);
        }

        // 1. Get the existing employee
        employee existing = optional.get();

        // 2. Update fields manually
        existing.setName(request.getName());
        existing.setDepartment(request.getDepartment());
        existing.setEmail(request.getEmail());

        // 3. Save the updated employee
        employee updated = employeerepository.save(existing);
        EmployeePayload UserData = employeeMapper.toLoadResponse(updated);

        // 4. Return response
        return new EmployeeManagementResponse<>("00","Succes", UserData);
    }

    @Override
    public EmployeeManagementResponse<String> deleteEmployee(Long id) {
        if (!employeerepository.existsById(id)) {
            return new EmployeeManagementResponse<>("01", "Employee not found", null);
        }
        employeerepository.deleteById(id);
        return new EmployeeManagementResponse<>("00", "Employee deleted successfully", "Deleted ID: " + id);
    }
}
