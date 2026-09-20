package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Comite
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ComiteApiService {

    @POST("api/comites")
    suspend fun crearComite(
        @Body comite: Comite
    ): Response<Comite>

    @GET("api/comites")
    suspend fun listarComites(): Response<List<Comite>>
}