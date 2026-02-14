package com.dee.secure_api.service;

import com.dee.secure_api.config.JwtUtils;
import com.dee.secure_api.dto.*;
import com.dee.secure_api.entity.ERole;
import com.dee.secure_api.entity.Role;
import com.dee.secure_api.entity.User;
import com.dee.secure_api.repository.RoleRepository;
import com.dee.secure_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public ApiResponse<?> registeruser(UserRegisReq dto) {
        // Check duplicate username/email
        if (userRepository.existsByUsername(dto.getUsername())) {
            return new ApiResponse<>("error", "01",
                    Map.of("errorMessage", "Username already exists"));
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return new ApiResponse<>("error", "02",
                    Map.of("errorMessage", "Email already exists"));
        }

        // Map DTO to entity
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 🔑 Set default role (from enum)
        Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRoles().add(defaultRole);

        // Save user
        User savedUser = userRepository.save(user);

        // Convert to UserData DTO
        Set<String> roleNames = savedUser.getRoles()
                .stream()
                .map(role -> role.getName().name()) // ERole → String
                .collect(Collectors.toSet());

        UserData userData = new UserData(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                roleNames
        );


        return new ApiResponse<>("success", "00", userData);
    }

    @Override
    public ApiResponse<JwtResponse> loginuser(UserLoginReq dto){
        // 1. Find user
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate password
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new ApiResponse<>("error", "03",null);
        }

        // 3. Generate JWT
        //String token = jwtUtils.generateToken(user.getUsername());
        String token = jwtUtils.generateNewToken(user.getUsername(), user.getEmail());
        // 4. Build response
        JwtResponse jwtResponse = new JwtResponse(
                token,
                "Bearer",
                jwtUtils.getExpirationMs()
        );

        return new ApiResponse<>("success", "00", jwtResponse);
    }
}
