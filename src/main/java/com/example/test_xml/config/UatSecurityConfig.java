package com.example.test_xml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("uat")
public class UatSecurityConfig extends SecurityConfig {

    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/common/v0/get-transactions",
                                "/common/v0/get-user-types",
                                "/common/v0/get-transaction-xml")
                        .permitAll()
                        .requestMatchers(
                                "/common/v0/get-reseller-details",
                                "/common/v0/get-merchant-details",
                                "/common/v0/get-customer-details")
                        .denyAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
