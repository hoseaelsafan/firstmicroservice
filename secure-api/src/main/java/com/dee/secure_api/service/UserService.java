package com.dee.secure_api.service;

import com.dee.secure_api.dto.ApiResponse;
import com.dee.secure_api.dto.UserData;

public interface UserService {
    ApiResponse<UserData> getCurrentUserProfile();
}
