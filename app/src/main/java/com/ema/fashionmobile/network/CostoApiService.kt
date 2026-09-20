package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Costo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CostoApiService {

    @POST("api/costos")
    suspend fun crearCosto(
        @Body costo: Costo
    ): Response<Costo>

    @GET("api/costos")
    suspend fun listarCostos(): Response<List<Costo>>
}