package com.rmxdev.pagosven.domain.useCase.loginUseCase

import com.rmxdev.pagosven.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.loginUser(email, password)
    }
}