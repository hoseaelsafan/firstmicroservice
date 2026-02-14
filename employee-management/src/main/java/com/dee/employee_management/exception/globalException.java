package com.dee.employee_management.exception;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class globalException {
    //handle @valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeManagementResponse<?>> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(java.util.stream.Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Validation Error"
                ));
        return ResponseEntity.badRequest().body(
                new EmployeeManagementResponse<>("04","Bad Request", errors));
    }
}
