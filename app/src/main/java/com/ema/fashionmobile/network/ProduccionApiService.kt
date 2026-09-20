package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Produccion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProduccionApiService {

    @POST("api/producciones")
    suspend fun crearProduccion(
        @Body produccion: Produccion
    ): Response<Produccion>

    @GET("api/producciones")
    suspend fun listarProducciones(): Response<List<Produccion>>
}