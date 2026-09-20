package com.ema.fashionmobile.network

import com.ema.fashionmobile.model.Diseno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DisenoApiService {

    @POST("api/disenos")
    suspend fun crearDiseno(
        @Body diseno: Diseno
    ): Response<Diseno>

    @GET("api/disenos")
    suspend fun listarDisenos(): Response<List<Diseno>>
}