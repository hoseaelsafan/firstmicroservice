package com.dee.secure_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "requestId";
    private static final String HEADER = "X-Request-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Reuse incoming header (important for microservices later)
        String requestId = request.getHeader(HEADER);

        // 2. Generate if missing
        if (requestId == null || requestId.isBlank()) {
            requestId = "req-" + UUID.randomUUID();
        }

        // 3. Put into MDC
        MDC.put(REQUEST_ID, requestId);

        try {
            // 4. Return it in response (nice for debugging)
            response.setHeader(HEADER, requestId);

            filterChain.doFilter(request, response);

        } finally {
            // 5. Clean up (VERY IMPORTANT)
            MDC.remove(REQUEST_ID);
        }
    }

}
