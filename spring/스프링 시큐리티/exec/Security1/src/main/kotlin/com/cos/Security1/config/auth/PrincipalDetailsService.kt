package com.cos.Security1.config.auth

import com.cos.Security1.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.NullPointerException

@Service
class PrincipalDetailsService(val userRepository: UserRepository):UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null){
            throw IllegalArgumentException("잘못된 username 입력")
        }
        val userEntity =
            userRepository.findByUsername(username)?:throw UsernameNotFoundException("존재하지 않는 username 입니다.")
        return PrincipalDetails(userEntity)
    }
}