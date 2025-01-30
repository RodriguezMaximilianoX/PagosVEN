package com.rmxdev.pagosven.presenter.signup

sealed class SignupState {
    object Idle : SignupState()
    object Loading : SignupState()
    object Success: SignupState()
    data class Error(val message: String) : SignupState()
}