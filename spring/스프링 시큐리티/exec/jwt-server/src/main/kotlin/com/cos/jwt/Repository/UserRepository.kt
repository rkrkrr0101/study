package com.cos.jwt.Repository

import com.cos.jwt.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Long> {
    fun findByUsername(username:String):User?
}