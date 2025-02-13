package com.rmxdev.pagosven.domain.useCase.getReceiverInfoUseCase

import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.domain.repository.TransferMoneyRepository
import javax.inject.Inject

class GetReceiverInfoUseCase @Inject constructor(
    private val repository: TransferMoneyRepository
) {
    suspend operator fun invoke(identifier: String): Result<User> {
        val receiver = repository.findUserByIdentifier(identifier)
            ?: return Result.failure(Exception("No se encontró el destinatario"))

        return Result.success(receiver)
    }
}