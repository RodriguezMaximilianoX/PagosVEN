package com.rmxdev.pagosven.domain.entities

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val dni: String,
    val phone: String,
    val email: String,
    val password: String,
    val alias: String,
    val balance: Float
)