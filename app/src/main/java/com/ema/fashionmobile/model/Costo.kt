package com.ema.fashionmobile.model

data class Costo(
    val id: Long? = null,
    val codigoReferencia: String,
    val costoMateriales: Double,
    val costoManoObra: Double,
    val otrosCostos: Double,
    val costoTotal: Double? = null
)