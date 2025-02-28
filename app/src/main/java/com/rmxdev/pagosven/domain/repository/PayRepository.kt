package com.rmxdev.pagosven.domain.repository

interface PayRepository {
    suspend fun pay(payerId: String, receiverId: String, amount: Double): Result<Unit>
}