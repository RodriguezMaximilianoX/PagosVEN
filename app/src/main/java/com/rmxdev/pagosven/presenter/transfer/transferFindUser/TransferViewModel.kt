package com.rmxdev.pagosven.presenter.transfer.transferFindUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.pagosven.domain.useCase.getReceiverInfoUseCase.GetReceiverInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getReceiverInfoUseCase: GetReceiverInfoUseCase
) : ViewModel() {

    private val _transferState = MutableStateFlow<TransferState>(TransferState.Idle)
    val transferState: StateFlow<TransferState> = _transferState

    fun fetchReceiverInfo(identifier: String) {
        viewModelScope.launch {
            _transferState.value = TransferState.Loading
            val result = getReceiverInfoUseCase(identifier)
            result.fold(
                onSuccess = { receiver ->
                    _transferState.value = TransferState.Success(receiver)
                },
                onFailure = { error ->
                    _transferState.value = TransferState.Error(error.message ?: "Error desconocido")
                }
            )
        }
    }
}