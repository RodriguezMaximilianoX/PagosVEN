package com.rmxdev.pagosven.presenter.home

sealed class NameState {
    object Loading : NameState()
    data class Success(val name: String) : NameState()
    data class Error(val message: String) : NameState()
}