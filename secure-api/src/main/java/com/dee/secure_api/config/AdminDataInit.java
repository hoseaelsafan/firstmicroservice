package com.dee.secure_api.config;

import com.dee.secure_api.entity.ERole;
import com.dee.secure_api.entity.Role;
import com.dee.secure_api.entity.User;
import com.dee.secure_api.repository.RoleRepository;
import com.dee.secure_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDataInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        // ✅ Ensure roles exist
        Role roleUser = roleRepository.findByName(ERole.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(null, ERole.ROLE_USER)));

        Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(null, ERole.ROLE_ADMIN)));

        // ✅ Ensure superuser exists
        if (!userRepository.existsByUsername("Bigadmin")) {
            User superUser = new User();
            superUser.setUsername("Bigadmin");
            superUser.setEmail("bigadmin@example.com");
            superUser.setPassword(passwordEncoder.encode("admin123")); // 🔐 Always encode!
            superUser.getRoles().add(roleAdmin);
            superUser.getRoles().add(roleUser);
            userRepository.save(superUser);

            System.out.println("🚀 Superuser 'Bigadmin' created with password: admin123");
        } else {
            System.out.println("✅ Superuser 'Bigadmin' already exists, skipping...");
        }
    }
}
