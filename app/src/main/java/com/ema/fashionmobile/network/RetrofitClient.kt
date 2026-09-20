package com.ema.fashionmobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.80.16:8081/"

    val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val disenoApi: DisenoApiService by lazy {
        retrofit.create(DisenoApiService::class.java)
    }

    val patronajeApi: PatronajeApiService by lazy {
        retrofit.create(PatronajeApiService::class.java)
    }

    val produccionApi: ProduccionApiService by lazy {
        retrofit.create(ProduccionApiService::class.java)
    }

    val costoApi: CostoApiService by lazy {
        retrofit.create(CostoApiService::class.java)
    }

    val comiteApi: ComiteApiService by lazy {
        retrofit.create(ComiteApiService::class.java)
    }

    val prendaApi: PrendaApiService by lazy {
        retrofit.create(PrendaApiService::class.java)
    }
}