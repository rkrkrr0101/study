package com.cos.jwt.config

import com.cos.jwt.Repository.UserRepository
import com.cos.jwt.filter.MyFilter1
import com.cos.jwt.jwt.JwtAuthFilter
import com.cos.jwt.jwt.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.context.SecurityContextHolderFilter
import org.springframework.web.filter.CorsFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig(val corsFilter: CorsFilter,
                     val authenticationConfiguration: AuthenticationConfiguration,
                     val userRepository: UserRepository) {

    @Bean
    fun passwordEncoder():BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        //http.addFilterBefore(MyFilter1(),SecurityContextHolderFilter::class.java)

        http.csrf(CsrfConfigurer<HttpSecurity>::disable)
        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        http.addFilter(corsFilter)

        http.addFilter(JwtAuthFilter(authenticationManager(authenticationConfiguration)))
        http.addFilter(JwtAuthorizationFilter(authenticationManager(authenticationConfiguration),userRepository))

        http.authorizeHttpRequests {
            it.requestMatchers("/api/v1/user/**")
                .hasAnyRole("MANAGER","ADMIN","USER")
            it.requestMatchers("/api/v1/manager/**")
                .hasAnyRole("MANAGER","ADMIN")
            it.requestMatchers("/api/v1/admin/**")
                .hasAnyRole("ADMIN")
            it.anyRequest().permitAll()
        }
        http.formLogin {
            it.disable()
        }
        http.httpBasic {
            it.disable()
        }

        return http.build()
    }


    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }



}