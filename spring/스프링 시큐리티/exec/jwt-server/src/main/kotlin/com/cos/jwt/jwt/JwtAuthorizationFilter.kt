package com.cos.jwt.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.cos.jwt.Repository.UserRepository
import com.cos.jwt.auth.PrincipalDetails
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
//권한이나 인증이 필요한 특정 url을 요청하면 무조건 탐
class JwtAuthorizationFilter(authManager:AuthenticationManager,val userRepository: UserRepository) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        println("인증서버동작")
        val jwtHeader = request.getHeader("Authorization")
        println("jwtHeader = ${jwtHeader}")
        if (jwtHeader==null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request, response)
            return
        }
        val jwtToken = request.getHeader("Authorization").replace("Bearer ","")
        val username = JWT.require(Algorithm.HMAC512("abc"))//토큰만들때 넣은 시크릿값
            .build().verify(jwtToken).getClaim("username")
            .asString()
        if (username!=null){ //서명확인
            val userEntity = userRepository.findByUsername(username)
                ?:throw IllegalArgumentException("유저아님")
            val principalDetails = PrincipalDetails(userEntity)
            val authentication:Authentication = UsernamePasswordAuthenticationToken( //강제로그인,해당유저존재확인했으니 가능
                principalDetails,
                null,   //null은 패스워드인데 필요없음
                principalDetails.authorities)//권한
            SecurityContextHolder.getContext().authentication=authentication//시큐리티세션공간

            chain.doFilter(request, response)

        }


    }
}
