package com.cos.jwt.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component


class MyFilter1:Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req: HttpServletRequest =request as HttpServletRequest
        val res: HttpServletResponse =response as HttpServletResponse

        if (req.method=="POST"){
            val headerAuth = req.getHeader("Authorization")
            println("headerAuth = ${headerAuth}")
            if (headerAuth=="cos"){
                chain?.doFilter(request, response)
            }else{
                val writer = res.writer
                writer.println("인증안됨")
            }
        }

        println("필터1작업")

    }
}