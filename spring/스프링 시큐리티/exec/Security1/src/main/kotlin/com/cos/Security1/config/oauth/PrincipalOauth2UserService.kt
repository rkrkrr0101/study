package com.cos.Security1.config.oauth

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class PrincipalOauth2UserService: DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        println("userRequest = ${userRequest?.clientRegistration?:"null"}")
        println("accessToken = ${userRequest?.accessToken?.tokenValue?:"null"}")
        println("registrationId = ${userRequest?.clientRegistration?.registrationId ?:"null"}")
        println("userRequest = ${super.loadUser(userRequest).attributes}")
        println("userRequest = ${super.loadUser(userRequest).attributes}")
        return super.loadUser(userRequest)
    }
}