package com.rmxdev.pagosven.domain.useCase.signupUseCase

import com.rmxdev.pagosven.domain.repository.UserRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        name: String,
        surname: String,
        dni: String,
        phone: String,
        alias: String,
        email: String,
        password: String
    ): Result<Unit> {
        return repository.signupUser(name, surname, dni, phone, alias, email, password)
    }
}