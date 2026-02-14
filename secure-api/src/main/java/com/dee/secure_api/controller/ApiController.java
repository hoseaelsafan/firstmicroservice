package com.dee.secure_api.controller;

import com.dee.secure_api.dto.ApiResponse;
import com.dee.secure_api.dto.UserData;
import com.dee.secure_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;

    @GetMapping("/api/user/me")
    public ResponseEntity<ApiResponse<UserData>> getProfile() {
        ApiResponse<UserData> response = userService.getCurrentUserProfile();
        return ResponseEntity.ok(response);
    }
}
