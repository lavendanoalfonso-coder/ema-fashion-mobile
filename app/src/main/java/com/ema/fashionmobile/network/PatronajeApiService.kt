package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Patronaje
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatronajeApiService {

    @POST("api/patronajes")
    suspend fun crearPatronaje(
        @Body patronaje: Patronaje
    ): Response<Patronaje>

    @GET("api/patronajes")
    suspend fun listarPatronajes(): Response<List<Patronaje>>
}