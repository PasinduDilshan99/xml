//package com.example.test_xml.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@Profile("dev")
//public class DevSecurityConfig extends SecurityConfig {
//
//    @Override
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/public/**", "/login", "/register").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return httpSecurity.build();
//    }
//}
