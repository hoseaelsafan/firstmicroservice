package com.dee.secure_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisReq {
    @NotBlank(message = "user name not blank")
    private String username;

    private String password;
    private String email;
}
