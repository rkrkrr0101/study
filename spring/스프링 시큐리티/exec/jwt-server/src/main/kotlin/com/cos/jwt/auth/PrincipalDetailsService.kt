package com.cos.jwt.auth

import com.cos.jwt.Repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class PrincipalDetailsService (val userRepository: UserRepository):UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {
        println("PrincipalDetailsService동작")
        if (username==null){
            throw IllegalArgumentException()
        }
        val user = userRepository.findByUsername(username)?:throw IllegalArgumentException()
        return PrincipalDetails(user)
    }
}