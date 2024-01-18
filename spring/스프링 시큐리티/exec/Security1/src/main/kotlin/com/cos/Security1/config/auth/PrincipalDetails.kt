package com.cos.Security1.config.auth

import com.cos.Security1.controller.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Arrays

class PrincipalDetails(val user:User):UserDetails {



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