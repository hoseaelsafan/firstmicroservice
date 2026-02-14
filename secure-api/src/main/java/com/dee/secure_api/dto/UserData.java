package com.dee.secure_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserData {
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}
