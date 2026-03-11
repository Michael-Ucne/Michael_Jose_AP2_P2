package com.phantomshard.michael_jose_ap2_p2.data.remotedatasource

import com.phantomshard.michael_jose_ap2_p2.data.remote.Resource
import com.phantomshard.michael_jose_ap2_p2.data.remote.SnakeApi
import com.phantomshard.michael_jose_ap2_p2.data.remote.dto.JugadorRequest
import com.phantomshard.michael_jose_ap2_p2.data.remote.dto.toDomain
import com.phantomshard.michael_jose_ap2_p2.domain.model.Jugador
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: SnakeApi
) {
    suspend fun getJugadores(): Resource<List<Jugador>> {
        return try {
            val response = api.getJugadores()
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.map { it.toDomain() }
                Resource.Success(data)
            } else {
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error conexión: ${e.message}")
        }
    }

    suspend fun getJugador(id: Int): Resource<Jugador> {
        return try {
            val response = api.getJugadorById(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Jugador no encontrado")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    suspend fun saveJugador(nombres: String, email: String): Resource<Jugador> {
        return try {
            val response = api.saveJugador(JugadorRequest(nombres, email))
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Error al guardar")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

    suspend fun updateJugador(id: Int, nombres: String, email: String): Resource<Unit> {
        return try {
            val response = api.updateJugador(id, JugadorRequest(nombres, email))
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Error al actualizar")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }
}
