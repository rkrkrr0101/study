package com.example.demo.core.domain.orders

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

@Entity
data class Orders(

    var orderItem:String,
    var price:Int,
    var orderDate:Date,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id:Int=0,
) {
}