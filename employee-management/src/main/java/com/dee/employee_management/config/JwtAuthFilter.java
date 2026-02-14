package com.dee.employee_management.config;

import com.dee.employee_management.service.customEmail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final customEmail customEmail;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("❌ Missing or invalid Authorization header");
            return; // ❌ stop here
        }

        final String jwt = authHeader.substring(7);
        final String email = jwtUtils.validateEmail(jwt);

        try {
            Boolean valid = jwtUtils.validateToken(jwt);

            if (Boolean.TRUE.equals(valid)) {
                if(Boolean.TRUE.equals(customEmail.getByemail(email))) {
                    System.out.println("✅ Token is valid, request allowed.");
                    filterChain.doFilter(request, response);// ✅ continue to controller
                }else  {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("🚫 User and email is not found");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("🚫 Token invalid or expired");
                return; // ❌ stop here
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("❌ Invalid token: " + e.getMessage());
            return; // ❌ stop here

        }
    }
}
