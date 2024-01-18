package com.cos.Security1.controller.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp

import java.sql.Timestamp

@Entity
class User(
    var username:String,
    var password:String,
    var email:String,
    var role:String="ROLE_USER",
    var provider:String,
    var providerId:String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int=0,
    @CreationTimestamp
    var createDate:Timestamp=Timestamp(System.currentTimeMillis()) ,
) {
    override fun toString(): String {
        return "User(username='$username', password='$password', email='$email', role=$role, id=$id, createDate=$createDate)"
    }
}