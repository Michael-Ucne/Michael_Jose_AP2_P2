package com.phantomshard.michael_jose_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.domain.usecases.GetJugadoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorListViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JugadorListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getJugadores()
    }

    fun getJugadores() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getJugadoresUseCase()
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            jugadores = result.data ?: emptyList()
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> { }
            }
        }
    }
}
