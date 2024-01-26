package com.cos.jwt.config

import com.cos.jwt.filter.MyFilter1
import com.cos.jwt.filter.MyFilter2
import com.cos.jwt.filter.MyFilter3
import com.cos.jwt.jwt.JwtAuthFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration


class FilterConfig(val authenticationConfiguration: AuthenticationConfiguration) {


    @Bean
    fun filter1():FilterRegistrationBean<MyFilter1>{
        val bean:FilterRegistrationBean<MyFilter1> =FilterRegistrationBean(MyFilter1())

        bean.addUrlPatterns("/*")
        bean.order=0 //낮은게 우선실행
        return bean;

    }
    @Bean
    fun filter2():FilterRegistrationBean<MyFilter2>{
        val bean:FilterRegistrationBean<MyFilter2> =FilterRegistrationBean(MyFilter2())
        bean.addUrlPatterns("/*")
        bean.order=1 //낮은게 우선실행
        return bean;

    }
    @Bean
    fun filter3():FilterRegistrationBean<MyFilter3>{
        val bean:FilterRegistrationBean<MyFilter3> =FilterRegistrationBean(MyFilter3())
        bean.addUrlPatterns("/*")
        bean.order=2 //낮은게 우선실행
        return bean;

    }
}