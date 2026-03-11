package com.phantomshard.michael_jose_ap2_p2.presentation.list

import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador

data class JugadorListUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null
)
