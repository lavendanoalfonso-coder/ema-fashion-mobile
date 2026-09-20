package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Prenda
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PrendaApiService {

    @POST("api/prendas")
    suspend fun crearPrenda(
        @Body prenda: Prenda
    ): Response<Prenda>

    @GET("api/prendas")
    suspend fun listarPrendas(): Response<List<Prenda>>
}