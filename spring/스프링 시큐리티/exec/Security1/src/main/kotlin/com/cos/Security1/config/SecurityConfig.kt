package com.cos.Security1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http.csrf(CsrfConfigurer<HttpSecurity>::disable)
        http.authorizeHttpRequests{
            it.requestMatchers("/user/**").authenticated()
            it.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
            it.requestMatchers("/admin/**").hasRole("ADMIN")
            it.anyRequest().permitAll()
        }
        http.formLogin{
            it.loginPage("/login")
            it.permitAll()
        }
        return http.build()
    }
}

