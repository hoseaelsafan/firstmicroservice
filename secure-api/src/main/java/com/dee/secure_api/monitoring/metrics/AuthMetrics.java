package com.dee.secure_api.monitoring.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class AuthMetrics {
    private final Counter loginSuccess;
    private final Counter loginFailure;
    private final Timer loginDuration;

    public AuthMetrics(MeterRegistry registry) {
        this.loginSuccess = Counter.builder("auth.login.success")
                .description("Number of successful logins")
                .register(registry);

        this.loginFailure = Counter.builder("auth.login.failure")
                .description("Number of failed logins")
                .register(registry);

        this.loginDuration = Timer.builder("auth.login.duration")
                .description("Time taken for login process")
                .register(registry);
    }

    public void success() {
        loginSuccess.increment();
    }

    public void failure() {
        loginFailure.increment();
    }

    public <T> T recordLogin(java.util.function.Supplier<T> action) {
        return loginDuration.record(action);
    }
}
