/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the configuration class for handling user authentication and access control 
 *  within the BookClub web application. It sets up in-memory users, defines password encoding, 
 *  and restricts access to authenticated users only, while supporting custom login and logout functionality.
 */
package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** 
     * This method creates an in-memory user details service with two users:
     * - user: assigned the USER role
     * - testuser01: assigned USER and ADMIN roles
     * @return an in-memory user details service
     */
    @Bean
    public UserDetailsService userDetailsService() {
        
        // Create a delegating password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        // Return in-memory user details manager with two users
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(encoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("testuser01")
                        .password(encoder.encode("password01"))
                        .roles("USER", "ADMIN")
                        .build()
        );
    }

    /** 
     * This method defines the application's security filter chain.
     * It restricts access to authenticated users only and defines custom behavior 
     * for login and logout functionality.
     * @param http the HttpSecurity configuration object
     * @return the configured security filter chain
     * @throws Exception if a security configuration error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Require authentication for all requests
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            // Set custom login page and allow public access to it
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            // Configure logout behavior and redirect after logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
            );

        return http.build();
    }
}
