package com.rmxdev.pagosven.presenter.transfer.transferAmount

sealed class AmountState {
    data object Idle : AmountState()
    data object Loading : AmountState()
    data object Success : AmountState()
    data class Error(val message: String) : AmountState()

}