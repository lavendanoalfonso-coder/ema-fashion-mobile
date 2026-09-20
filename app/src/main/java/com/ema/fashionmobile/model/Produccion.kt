package com.ema.fashionmobile.model

data class Produccion(
    val id: Long? = null,
    val codigoReferencia: String,
    val materiales: String,
    val tiempoConfeccion: Double
)