/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 *
 *  Test-only security config to disable authentication and CSRF for unit tests.
 */

package com.bookclub.security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Disables security for test environment to allow unrestricted access during MockMvc tests.
 */
@TestConfiguration
public class TestSecurityConfig {

    /**
     * Permits all requests and disables CSRF for testing.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            .csrf().disable();
        return http.build();
    }
}
