package com.rmxdev.pagosven.presenter.pay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rmxdev.pagosven.domain.useCase.payUseCase.PayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayViewModel @Inject constructor(
    private val payUseCase: PayUseCase,
    private val auth: FirebaseAuth
): ViewModel() {

    private val _payState = MutableStateFlow<PayState>(PayState.Idle)
    val payState: StateFlow<PayState> = _payState

    fun pay(receiverId: String, amount: Double) {
        val payerId = auth.currentUser?.uid ?: ""
        viewModelScope.launch {
            _payState.value = PayState.Loading
            val result = payUseCase(payerId, receiverId, amount)
            _payState.value = if (result.isSuccess) {
                PayState.Success
            } else {
                PayState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

}