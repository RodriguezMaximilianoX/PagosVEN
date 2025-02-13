package com.rmxdev.pagosven.domain.repository

import com.rmxdev.pagosven.domain.entities.User

interface TransferMoneyRepository {
    suspend fun getUserById(userId: String): User?
    suspend fun findUserByIdentifier(identifier: String): User?
    suspend fun executeTransaction(
        senderId: String,
        newSenderBalance: Float,
        receiverId: String,
        newReceiverBalance: Float
    ): Result<Unit>
}