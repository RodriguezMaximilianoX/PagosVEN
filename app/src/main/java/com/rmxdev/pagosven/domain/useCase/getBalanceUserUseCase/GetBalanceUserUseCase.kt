package com.rmxdev.pagosven.domain.useCase.getBalanceUserUseCase

import com.rmxdev.pagosven.domain.repository.UserRepository
import javax.inject.Inject

class GetBalanceUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String): Double {
        return userRepository.getBalanceUser()
    }
}