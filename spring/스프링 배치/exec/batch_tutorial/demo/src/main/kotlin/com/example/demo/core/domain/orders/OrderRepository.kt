package com.example.demo.core.domain.orders

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository:JpaRepository<Orders,Int> {
}