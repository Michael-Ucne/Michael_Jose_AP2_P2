package com.phantomshard.michael_jose_ap2_p2.domain.usecases

import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador
import com.phantomshard.michael_jose_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int): Resource<Jugador> = repository.getJugador(id)
}
