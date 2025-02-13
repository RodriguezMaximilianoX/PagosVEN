package com.rmxdev.pagosven.presenter.transfer.transferAmount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.domain.useCase.transferMoneyUseCase.TransferMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmountViewModel @Inject constructor(
    private val transferMoneyUseCase: TransferMoneyUseCase,
    auth: FirebaseAuth
): ViewModel() {

    private val userId = auth.currentUser?.uid
    private val senderId = userId ?: ""
    private val _amountState = MutableStateFlow<AmountState>(AmountState.Idle)
    val amountState: StateFlow<AmountState> = _amountState

    fun transferMoney(userReceiver: User, amount: Double) {
        viewModelScope.launch {
            _amountState.value = AmountState.Loading
            val result = transferMoneyUseCase(senderId, userReceiver, amount)
            _amountState.value = if (result.isSuccess) {
                AmountState.Success
            } else {
                AmountState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

}