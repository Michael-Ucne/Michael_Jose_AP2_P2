package com.phantomshard.michael_jose_ap2_p2.data.repository

import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.data.remotedatasource.JugadorRemoteDataSource
import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador
import com.phantomshard.michael_jose_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override suspend fun getJugadores(): Resource<List<Jugador>> {
        return remoteDataSource.getJugadores()
    }

    override suspend fun getJugador(id: Int): Resource<Jugador> {
        return remoteDataSource.getJugador(id)
    }

    override suspend fun saveJugador(nombres: String, email: String): Resource<Jugador> {
        return remoteDataSource.saveJugador(nombres, email)
    }

    override suspend fun updateJugador(id: Int, nombres: String, email: String): Resource<Unit> {
        return remoteDataSource.updateJugador(id, nombres, email)
    }
}
