package com.command.itdaserver.global.config;

import com.command.itdaserver.global.error.GlobalExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class SecurityFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final GlobalExceptionFilter globalExceptionFilter;

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(
                globalExceptionFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}

