package com.dee.secure_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReq {
    private String username;
    private String password;
}
