package com.cos.Security1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class DiSelectConfig {
    @Bean
    fun encodePwd(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}