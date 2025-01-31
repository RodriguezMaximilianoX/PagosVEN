package com.rmxdev.pagosven.presenter.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmxdev.pagosven.domain.useCase.getNameUserUseCase.GetNameUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNameUser: GetNameUserUseCase
) : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()
    private var isNameLoaded = false

    fun getUserName() {
        // Si ya se cargó el nombre, no lo volvemos a cargar
        if (isNameLoaded) return

        // Emitimos "Cargando..." mientras obtenemos el nombre
        _userName.value = "de nuevo"

        viewModelScope.launch {
            try {
                Log.d("HomeViewModel", "Obteniendo el nombre del usuario...")
                // Llamamos al repositorio para obtener el nombre
                val name = getNameUser()
                Log.d("HomeViewModel", "Nombre del usuario: ${_userName.value}")
                // Si el nombre cambia, lo actualizamos
                if (_userName.value != name) {
                    _userName.value = name
                    isNameLoaded = true // Marcamos que ya se cargó el nombre
                }
            } catch (e: Exception) {
                // Si hay error, lo manejamos
                _userName.value = "Error: ${e.message}"
            }
        }
    }
}



