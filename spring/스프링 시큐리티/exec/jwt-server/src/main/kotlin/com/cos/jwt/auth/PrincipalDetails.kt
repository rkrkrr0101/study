package com.cos.jwt.auth

import com.cos.jwt.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class PrincipalDetails(val user:User):UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val collect = ArrayList<GrantedAuthority>()
        user.roleList.forEach {
            collect.add(GrantedAuthority { return@GrantedAuthority it })
        }

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