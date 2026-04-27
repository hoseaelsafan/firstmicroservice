package com.dee.employee_management.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final RequestIdFilter requestidFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, RequestIdFilter requestidFilter) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.requestidFilter = requestidFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/api/employees")
                            .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                        .requestMatchers(HttpMethod.GET,"/api/employees/*")
                            .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/employees/register")
                            .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/*")
                            .hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/employees/*")
                            .hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(requestidFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
