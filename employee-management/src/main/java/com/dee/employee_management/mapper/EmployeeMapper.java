package com.dee.employee_management.mapper;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.EmployeePayload;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public employee toEntity(registerEmployeeRequest dto){
        employee empl = new employee();
        empl.setName(dto.getName());
        empl.setDepartment(dto.getDepartment());
        empl.setEmail(dto.getEmail());
        return empl;
    }
    public EmployeePayload toLoadResponse(employee entity){
        return new EmployeePayload(entity.getId(),
                entity.getName(),
                entity.getDepartment(),
                entity.getEmail(),
                entity.getCrtdate());
    }
    public List<EmployeePayload> toListLoadResponse(List<employee> entities) {
        return entities.stream()
                // Here is the lambda (->)
                .map(entity -> toLoadResponse(entity))
                .collect(Collectors.toList());
    }
}
