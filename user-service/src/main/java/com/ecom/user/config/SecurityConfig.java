package com.ecom.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/api/users/register",
//                                "/api/auth/login",
//                                "/api/password/forgot",
//                                "/api/password/reset"
//                        ).permitAll()
//                        .requestMatchers("/api/admin/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers("/api/profile/**")
//                        .hasAnyRole("USER", "ADMIN")
//
//                        .anyRequest().permitAll()
//                );
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}