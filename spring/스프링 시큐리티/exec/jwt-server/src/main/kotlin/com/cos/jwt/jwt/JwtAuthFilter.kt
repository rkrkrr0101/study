package com.cos.jwt.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.cos.jwt.auth.PrincipalDetails
import com.cos.jwt.model.User
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.util.*


class JwtAuthFilter(private val authenticationManager:AuthenticationManager):UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        request?:throw IllegalArgumentException()
        val om = ObjectMapper()
        val user = om.readValue(request.inputStream, User::class.java) //파싱

        val authToken = UsernamePasswordAuthenticationToken(user.username, user.password)//토큰생성,앞은 유저네임 뒤는 패스워드
        val authentication = authenticationManager.authenticate(authToken)//userDetailsService의 loadUserByUsername실행

        return authentication
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        if (response==null || authResult==null){
            throw IllegalArgumentException("잘못된입력")
        }
        val principalDetails = authResult.principal as PrincipalDetails

        val jwtToken=JWT.create()
            .withSubject("abc토큰")//토큰명,큰의미없음
            .withExpiresAt(Date(System.currentTimeMillis()+(60000*30)))//만료시간,좀 짧게잡는게좋음,여기선 30분
            .withClaim("id",principalDetails.user.id)//내가넣을권한이나 정보
            .withClaim("username",principalDetails.user.username)
            .sign(Algorithm.HMAC512("abc"))//hash암호방식,rsa아니라서 키값들어가는거,키값적으면됨

        response.addHeader("Authorization", "Bearer $jwtToken")
    }
}