package com.dee.secure_api.mapper;

import com.dee.secure_api.dto.JwtResponse;
import com.dee.secure_api.dto.UserRegisReq;
import com.dee.secure_api.entity.User;

public class JwtMapper {

    public User toEntity(UserRegisReq dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static JwtResponse toJwtResponse(String token, long expiresIn) {
        return new JwtResponse(token, "Bearer", expiresIn);
    }
}
