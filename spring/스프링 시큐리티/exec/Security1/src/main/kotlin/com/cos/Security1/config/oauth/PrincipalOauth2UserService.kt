package com.cos.Security1.config.oauth

import com.cos.Security1.config.auth.PrincipalDetails
import com.cos.Security1.config.oauth.provider.FacebookUserInfo
import com.cos.Security1.config.oauth.provider.GoogleUserInfo
import com.cos.Security1.config.oauth.provider.NaverUserInfo
import com.cos.Security1.config.oauth.provider.OAuth2UserInfo
import com.cos.Security1.controller.model.User
import com.cos.Security1.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class PrincipalOauth2UserService(val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val userRepository: UserRepository): DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        if (userRequest==null){
            throw IllegalArgumentException()
        }
        val oAuth2User = super.loadUser(userRequest)
        var oAuth2UserInfo:OAuth2UserInfo
        if (userRequest.clientRegistration.registrationId=="google"){
            println("구글요청")
            oAuth2UserInfo=GoogleUserInfo(oAuth2User.attributes)
        }else if(userRequest.clientRegistration.registrationId=="facebook"){
            println("페북요청")
            oAuth2UserInfo=FacebookUserInfo(oAuth2User.attributes)
        }else if(userRequest.clientRegistration.registrationId=="naver"){
            println("네이버요청")
            @Suppress("UNCHECKED_CAST")
            oAuth2UserInfo=NaverUserInfo(oAuth2User.attributes["response"] as Map<String, Any>)
        } else{
            throw IllegalArgumentException()
        }


        val provider = oAuth2UserInfo.getProvider()
        val providerId = oAuth2UserInfo.getProviderId()
        val username="""${provider}_${providerId}"""
        val password=bCryptPasswordEncoder.encode("qwer")
        val email = oAuth2UserInfo.getEmail()
        val role = "ROLE_USER"
        var userEntity = userRepository.findByUsername(username)
        if (userEntity==null){
            userEntity=User(username,password,email,role,provider,providerId)
            userRepository.save(userEntity)
        }

        return PrincipalDetails(userEntity,oAuth2User.attributes)
    }
}