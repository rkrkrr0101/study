package com.cos.jwt.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class User(
    var username:String,
    var password:String,
    var roles:String="ROLE_USER",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0,
) {
    val roleList:List<String>
        get() {
            if(this.roles.isNotEmpty()){
                println("토토로")
                return this.roles.split(",")
            }
            return listOf()
        }


}