package com.example.demo.core.domain.accounts

import com.example.demo.core.domain.orders.Orders
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.*

@Entity
data class Accounts(

    var orderItem:String,
    var price:Int,
    var orderDate: Date,
    var accountDate:Date,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id:Int=0,
) {
    companion object{
        fun orderToAccount(orders: Orders):Accounts{
            return Accounts(orders.orderItem,orders.price,orders.orderDate, Date())
        }
    }

}