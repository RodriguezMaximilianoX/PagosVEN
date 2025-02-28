package com.rmxdev.pagosven.presenter.qr

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.pagosven.data.network.QrCodeGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrViewModel @Inject constructor(
    private val qrCodeGenerator: QrCodeGenerator
): ViewModel() {

    private val _qrState = MutableStateFlow<QrState>(QrState.Initial)
    val qrState: StateFlow<QrState> = _qrState

    private val _qrCodeBitmap = MutableStateFlow<Bitmap?>(null)
    val qrCodeBitmap: StateFlow<Bitmap?> = _qrCodeBitmap

    fun qrCode(amount: String, userId: String) {
        viewModelScope.launch {
            _qrState.value = QrState.Loading
            try {
                _qrCodeBitmap.value = qrCodeGenerator.generateQrCode(amount, userId)
                _qrState.value = QrState.Success
            }catch (e: Exception){
                _qrState.value = QrState.Error(e.message ?: "Error al cargar el codigo QR")
            }
        }

    }

    fun backToHome(navigateToHome: () -> Unit){
        viewModelScope.launch {
            _qrState.value = QrState.Success
            delay(30000)
            navigateToHome()
            _qrState.value = QrState.Initial
        }
    }
}