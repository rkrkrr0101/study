package com.cos.Security1.repository

import com.cos.Security1.controller.model.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository:JpaRepository<User,Int> {
    fun findByUsername(username:String):User?
}