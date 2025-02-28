package com.rmxdev.pagosven.presenter.pay

sealed class PayState {
    data object Idle : PayState()
    data object Loading : PayState()
    data object Success : PayState()
    data class Error(val message: String) : PayState()

}