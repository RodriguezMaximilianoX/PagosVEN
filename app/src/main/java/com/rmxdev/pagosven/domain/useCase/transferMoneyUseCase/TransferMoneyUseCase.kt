package com.rmxdev.pagosven.domain.useCase.transferMoneyUseCase

import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.domain.repository.TransferMoneyRepository
import javax.inject.Inject

class TransferMoneyUseCase @Inject constructor(
    private val repository: TransferMoneyRepository
) {
    suspend operator fun invoke(senderId: String, receiver: User, amount: Float): Result<Unit> {
        if (amount <= 0) return Result.failure(Exception("El monto debe ser mayor a 0"))

        val sender = repository.getUserById(senderId)
            ?: return Result.failure(Exception("No se encontró el remitente"))
        if (sender.balance < amount) return Result.failure(Exception("Saldo insuficiente"))

        val newSenderBalance = sender.balance - amount
        val newReceiverBalance = receiver.balance + amount

        return repository.executeTransaction(senderId, newSenderBalance, receiver.id, newReceiverBalance)

    }
}