package com.cos.jwt.controller

import com.cos.jwt.Repository.UserRepository
import com.cos.jwt.auth.PrincipalDetails
import com.cos.jwt.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RestApiController(val userRepository: UserRepository,
                        val bCryptPasswordEncoder:BCryptPasswordEncoder) {

    @GetMapping("/home")
    fun home():String{
        val user = User("ab", "abc", "aa,bb,qq")
        println(user.roleList)
        return "<h1>home</h1>"
    }
    @PostMapping("/token")
    fun token():String{
        return "<h1>token</h1>"
    }
    @PostMapping("/join")
    fun join(@RequestBody user:User):String{
        user.password=bCryptPasswordEncoder.encode(user.password)
        user.roles=("ROLE_MANAGER")
        userRepository.save(user)
        return "회원가입완료"
    }

    @GetMapping("/api/v1/user")
    fun user(authentication: Authentication):String{
        val principal = authentication.principal as PrincipalDetails
        println("principal = ${principal.username}")
        return "user"
    }
    @GetMapping("/api/v1/manager")
    fun manager():String{
        return "manager"
    }
    @GetMapping("/api/v1/admin")
    fun admin():String{
        return "admin"
    }
}