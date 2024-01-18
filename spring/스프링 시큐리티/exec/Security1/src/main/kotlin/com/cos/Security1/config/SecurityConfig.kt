package com.cos.Security1.config

import com.cos.Security1.config.oauth.PrincipalOauth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig(val principalOauth2UserService: PrincipalOauth2UserService) {
    @Bean
    fun encodePwd():BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

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
            it.loginPage("/loginForm")
            it.loginProcessingUrl("/login")
            //it.permitAll()
            it.defaultSuccessUrl("/")

        }
        http.oauth2Login {
            it.loginPage("/loginForm")
            it.userInfoEndpoint{
                it.userService(principalOauth2UserService)
            }
        }

        return http.build()
    }
}

