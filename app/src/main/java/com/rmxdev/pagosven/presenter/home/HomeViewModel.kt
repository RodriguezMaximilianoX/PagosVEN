package com.rmxdev.pagosven.presenter.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rmxdev.pagosven.domain.useCase.getBalanceUserUseCase.GetBalanceUserUseCase
import com.rmxdev.pagosven.domain.useCase.getNameUserUseCase.GetNameUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNameUser: GetNameUserUseCase,
    private val getBalanceUser: GetBalanceUserUseCase,
    auth: FirebaseAuth
) : ViewModel() {

    private val _scannedQrContent = MutableStateFlow<String?>(null)
    val scannedQrContent: StateFlow<String?> = _scannedQrContent

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _userBalance = MutableStateFlow(0.0)
    val userBalance: StateFlow<Double> = _userBalance.asStateFlow()

    private val _userId: String? = auth.currentUser?.uid
    val userId: String? = _userId


    init {
        _userId?.let {
            loadUserData(it) // Cargar nombre y balance al iniciar el ViewModel
        }
    }

    private fun loadUserData(userId: String) {
        viewModelScope.launch {
            try {
                _userName.value = getNameUser(userId)
                _userBalance.value = getBalanceUser(userId)
            } catch (e: Exception) {
                _userName.value = "Error: ${e.message}"
                _userBalance.value = 0.0
            }
        }
    }

    fun processScannedQr(qrContent: String) {
        Log.d("InitialViewModel", "processScannedQr: Updating scannedQrContent with $qrContent")
        _scannedQrContent.value = qrContent
    }
}



