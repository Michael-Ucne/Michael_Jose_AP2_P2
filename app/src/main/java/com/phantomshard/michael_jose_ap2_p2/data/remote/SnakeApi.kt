package com.phantomshard.michael_jose_ap2_p2.data.remote

import com.phantomshard.michael_jose_ap2_p2.data.remote.dto.JugadorRequest
import com.phantomshard.michael_jose_ap2_p2.data.remote.dto.JugadorResponse
import retrofit2.Response
import retrofit2.http.*

interface SnakeApi {
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<JugadorResponse>>

    @GET("api/Jugadores/{id}")
    suspend fun getJugadorById(@Path("id") id: Int): Response<JugadorResponse>

    @POST("api/Jugadores")
    suspend fun saveJugador(@Body jugadorRequest: JugadorRequest): Response<JugadorResponse>

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(@Path("id") id: Int, @Body jugadorRequest: JugadorRequest): Response<Unit>

    @DELETE("api/Jugadores/{id}")
    suspend fun deleteJugador(@Path("id") id: Int): Response<Unit>
}
