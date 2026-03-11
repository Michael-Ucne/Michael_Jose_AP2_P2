package com.phantomshard.michael_jose_ap2_p2.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.domain.usecases.GetJugadorUseCase
import com.phantomshard.michael_jose_ap2_p2.domain.usecases.SaveJugadorUseCase
import com.phantomshard.michael_jose_ap2_p2.domain.usecases.UpdateJugadorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorDetailViewModel @Inject constructor(
    private val getJugadorUseCase: GetJugadorUseCase,
    private val saveJugadorUseCase: SaveJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JugadorDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun onNombresChange(nombres: String) {
        _uiState.update { it.copy(nombres = nombres) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun loadJugador(id: Int) {
        if (id <= 0) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getJugadorUseCase(id)
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            jugador = result.data,
                            nombres = result.data?.nombres ?: "",
                            email = result.data?.email ?: ""
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {}
            }
        }
    }

    fun saveJugador() {
        viewModelScope.launch {
            val currentState = _uiState.value
            
            // Validaciones
            if (currentState.nombres.isBlank()) {
                _uiState.update { it.copy(error = "El campo Nombres no puede estar vacío") }
                return@launch
            }
            if (currentState.email.isBlank()) {
                _uiState.update { it.copy(error = "El campo Email es obligatorio") }
                return@launch
            }
            if (!currentState.email.contains("@")) {
                _uiState.update { it.copy(error = "El campo Email debe tener un formato válido (contener @)") }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = if (currentState.jugador != null) {
                updateJugadorUseCase(currentState.jugador.jugadorId, currentState.nombres, currentState.email)
            } else {
                saveJugadorUseCase(currentState.nombres, currentState.email)
            }

            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {}
            }
        }
    }
}

