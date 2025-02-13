package com.rmxdev.pagosven.presenter.transfer.transferFindUser

import com.rmxdev.pagosven.domain.entities.User

sealed class TransferState {
    data object Idle : TransferState()
    data object Loading : TransferState()
    data class Success(val receiver: User) : TransferState()
    data class Error(val message: String) : TransferState()
}