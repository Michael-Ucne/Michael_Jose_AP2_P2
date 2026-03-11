package com.phantomshard.michael_jose_ap2_p2.presentation.detail

import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador

data class JugadorDetailUiState(
    val isLoading: Boolean = false,
    val jugador: Jugador? = null,
    val nombres: String = "",
    val email: String = "",
    val error: String? = null,
    val isSuccess: Boolean = false
)
