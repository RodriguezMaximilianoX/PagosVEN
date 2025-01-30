package com.rmxdev.pagosven.domain.repository

interface UserRepository {
    suspend fun loginUser(email: String, password: String): Result<Unit>
    suspend fun signupUser(
        name: String,
        surname: String,
        dni: String,
        phone: String,
        alias: String,
        email: String,
        password: String
    ): Result<Unit>
    suspend fun getNameUser(): Result<String>
}