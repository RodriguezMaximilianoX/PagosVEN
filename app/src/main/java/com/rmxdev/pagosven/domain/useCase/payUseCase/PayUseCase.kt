package com.rmxdev.pagosven.domain.useCase.payUseCase

import com.rmxdev.pagosven.domain.repository.PayRepository
import javax.inject.Inject

class PayUseCase @Inject constructor(
    private val repository: PayRepository
) {
    suspend operator fun invoke(payerId: String, receiverId: String, amount: Double): Result<Unit> {
        if (amount <= 0) return Result.failure(Exception("El monto debe ser mayor a 0"))

        return repository.pay(payerId, receiverId, amount)
    }
}