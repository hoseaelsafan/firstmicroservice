package com.dee.secure_api.controller;

import com.dee.secure_api.dto.*;
import com.dee.secure_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private static final Logger trxLog =
            LoggerFactory.getLogger("AUTH_LOGGER");

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody UserRegisReq dto) {
        trxLog.info("REGISTER_REQUEST username : {}", dto.getUsername());
        ApiResponse<?> response = authService.registeruser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody UserLoginReq dto){
        trxLog.info("LOGIN_REQUEST username : {}", dto.getUsername());
        ApiResponse<JwtResponse> response = authService.loginuser(dto);
        return ResponseEntity.ok(response);

    }

}
