package com.phantomshard.michael_jose_ap2_p2.domain.repository

import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador

interface JugadorRepository {
    suspend fun getJugadores(): Resource<List<Jugador>>
    suspend fun getJugador(id: Int): Resource<Jugador>
    suspend fun saveJugador(nombres: String, email: String): Resource<Jugador>
    suspend fun updateJugador(id: Int, nombres: String, email: String): Resource<Unit>
}
