package com.dee.secure_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long expired;

    public JwtResponse(String token, Long expired) {
        this.token = token;
        this.expired = expired;
    }
}
