package com.realm.jwt_security;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.context.annotation.Bean;

/**
 * ONLY loads for servlet-based apps (auth-service, booking-service, etc).
 * Never even attempted in a WebFlux app like api-gateway — that's what
 * both conditions below guarantee, independently of each other:
 * - ConditionalOnWebApplication(SERVLET): skip entirely in reactive apps
 * - ConditionalOnClass(Filter.class): extra safety — if jakarta.servlet
 * isn't even on the classpath, don't attempt to load this class at all
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass(jakarta.servlet.Filter.class)
public class JwtServletAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
        return new JwtAuthFilter(jwtUtil);
    }
}