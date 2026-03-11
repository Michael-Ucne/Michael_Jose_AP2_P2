package com.phantomshard.michael_jose_ap2_p2.domain.usecases

import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class UpdateJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int, nombres: String, email: String): Resource<Unit> = 
        repository.updateJugador(id, nombres, email)
}
