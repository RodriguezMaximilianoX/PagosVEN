package com.rmxdev.pagosven.domain.useCase.getNameUserUseCase

import com.rmxdev.pagosven.domain.repository.UserRepository
import javax.inject.Inject

class GetNameUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<String>{
        return repository.getNameUser()
    }
}