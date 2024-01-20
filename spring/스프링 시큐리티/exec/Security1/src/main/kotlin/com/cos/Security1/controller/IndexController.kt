package com.cos.Security1.controller

import com.cos.Security1.config.auth.PrincipalDetails
import com.cos.Security1.controller.model.User
import com.cos.Security1.repository.UserRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class IndexController(
    val userRepository: UserRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    @GetMapping("/test/login")
    @ResponseBody
    fun loginTest(authentication:Authentication,
                  @AuthenticationPrincipal userDetails: PrincipalDetails ):String{
        println("/test/login ===============")
        val principalDetails:PrincipalDetails=authentication.principal as PrincipalDetails
        println("authentication = ${principalDetails.user}")
        println("userDetails = ${userDetails.user}")
        return "세션정보확인"

    }
    @GetMapping("/test/oauth/login")
    @ResponseBody
    fun loginOauthTest(authentication:Authentication,
                       @AuthenticationPrincipal oAuth: OAuth2User):String{
        println("/test/oauth/login ===============")
        val oAuth2User:OAuth2User=authentication.principal as OAuth2User
        println("authentication = ${oAuth2User.attributes}")
        println("authentication = ${oAuth.attributes}")
        return "oauth세션정보확인"
    }

    @GetMapping(path=["","/"])
    fun index():String{
        return "index"
    }
    @GetMapping("/user")
    @ResponseBody
    fun user(@AuthenticationPrincipal principalDetails: PrincipalDetails):String{
        println("principalDetails = ${principalDetails.user}")
        return "user"
    }
    @GetMapping("/admin")
    @ResponseBody
    fun admin():String{
        return "admin"
    }
    @GetMapping("/manager")
    @ResponseBody
    fun manager():String{
        return "manager"
    }
    @GetMapping("/loginForm")
    fun loginForm():String{
        return "loginForm"
    }
    @GetMapping("/joinForm")
    fun joinForm():String{
        return "joinForm"
    }
    @PostMapping("/join")
    fun join(user: User):String{
        println("user = ${user}")
        user.role="ROLE_USER"
        val rawPassword = user.password
        val encPassword = bCryptPasswordEncoder.encode(rawPassword)
        user.password=encPassword
        userRepository.save(user)
        return "redirect:/loginForm"
    }
    @GetMapping("/info")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    fun info():String{
        return "methodManager"
    }
    @GetMapping("/data")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    fun data():String{
        return "data"
    }

}