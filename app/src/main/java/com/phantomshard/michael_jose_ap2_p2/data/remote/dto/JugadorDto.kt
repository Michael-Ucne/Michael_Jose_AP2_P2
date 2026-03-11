package com.phantomshard.michael_jose_ap2_p2.data.remote.dto

import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador

data class JugadorRequest(
    val nombres: String,
    val email: String
)

data class JugadorResponse(
    val jugadorId: Int,
    val nombres: String,
    val email: String
)

fun JugadorResponse.toDomain() = Jugador(
    jugadorId = jugadorId,
    nombres = nombres,
    email = email
)
