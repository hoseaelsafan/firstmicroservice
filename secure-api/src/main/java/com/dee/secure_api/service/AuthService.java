package com.dee.secure_api.service;

import com.dee.secure_api.dto.*;

public interface AuthService {
    ApiResponse<?> registeruser(UserRegisReq dto);

    ApiResponse<JwtResponse> loginuser(UserLoginReq dto);
}
