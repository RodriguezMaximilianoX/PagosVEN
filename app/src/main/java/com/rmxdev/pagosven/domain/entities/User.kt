package com.rmxdev.pagosven.domain.entities

data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val dni: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val alias: String = "",
    val balance: Double = 0.0
){
    constructor() : this("", "", "", "", "", "", "", "", 0.0)
}