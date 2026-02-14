package com.dee.secure_api.service;

import com.dee.secure_api.dto.ApiResponse;
import com.dee.secure_api.dto.UserData;
import com.dee.secure_api.entity.User;
import com.dee.secure_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public ApiResponse<UserData> getCurrentUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> roleNames = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        UserData userData = new UserData(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roleNames
        );

        return new ApiResponse<>("success", "00", userData);
    }

}
