package com.rmxdev.pagosven.presenter.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.pagosven.domain.useCase.signupUseCase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignupUseCase
) : ViewModel() {

    private val _signupState = MutableStateFlow<SignupState>(SignupState.Idle)
    val signupState: StateFlow<SignupState> = _signupState

    fun signupUser(
        name: String,
        surname: String,
        dni: String,
        phone: String,
        alias: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _signupState.value = SignupState.Loading
            _signupState.value = signUpUseCase(name, surname, dni, phone, alias, email, password).fold(
                onSuccess = {
                    SignupState.Success
                },
                onFailure = {
                    SignupState.Error(it.message ?: "Unknown error")
                }
            )
        }
        Log.d("SignUpViewModel", "signupUser: $name $surname $dni $phone $alias $email $password")
    }

}