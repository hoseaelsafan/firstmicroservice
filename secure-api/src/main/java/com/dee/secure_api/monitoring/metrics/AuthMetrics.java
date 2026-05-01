package com.dee.secure_api.monitoring.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class AuthMetrics {
    private final Counter loginSuccess;
    private final Counter loginFailure;

    public AuthMetrics(MeterRegistry registry) {
        this.loginSuccess = Counter.builder("auth.login.success")
                .description("Number of successful logins")
                .register(registry);

        this.loginFailure = Counter.builder("auth.login.failure")
                .description("Number of failed logins")
                .register(registry);
    }

    public void success() {
        loginSuccess.increment();
    }

    public void failure() {
        loginFailure.increment();
    }
}
