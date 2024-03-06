package com.example.demo.core.domain.accounts

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository:JpaRepository<Accounts,Int> {
}