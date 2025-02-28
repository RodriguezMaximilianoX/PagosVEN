package com.rmxdev.pagosven.presenter.qr

sealed class QrState {
    data object Initial : QrState()
    data object Loading : QrState()
    data object Success: QrState()
    data class Error(val message: String) : QrState()
}