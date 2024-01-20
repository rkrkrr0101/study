package com.cos.Security1.config.auth

import com.cos.Security1.controller.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.Arrays

class PrincipalDetails(val user:User):UserDetails,OAuth2User {
    private var attributes=mutableMapOf<String,Any>()
    constructor(user:User,attributes:MutableMap<String,Any>) : this(user) {
        this.attributes=attributes
    }

    override fun getName(): String {
        return ""
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attributes
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val collect = ArrayList<GrantedAuthority>()
        collect.add(GrantedAuthority { return@GrantedAuthority user.role })
        return collect
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}