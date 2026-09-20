package com.ema.fashionmobile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ema.fashionmobile.model.Produccion
import com.ema.fashionmobile.network.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun ProduccionScreen(
    onBack: () -> Unit
) {

    var referencia by remember { mutableStateOf("") }
    var materiales by remember { mutableStateOf("") }
    var tiempoConfeccion by remember { mutableStateOf("") }

    var mensaje by remember { mutableStateOf("") }
    var guardando by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    var producciones by remember {
        mutableStateOf<List<Produccion>>(emptyList())
    }

    val scope = rememberCoroutineScope()

    fun cargarProducciones() {

        scope.launch {

            cargando = true

            try {

                val respuesta =
                    RetrofitClient.produccionApi.listarProducciones()

                if (respuesta.isSuccessful) {

                    producciones = respuesta.body() ?: emptyList()

                } else {

                    mensaje =
                        "No se pudieron cargar las producciones. Código: ${respuesta.code()}"
                }

            } catch (e: Exception) {

                mensaje = "No se pudieron cargar las producciones"

            } finally {

                cargando = false
            }
        }
    }

    LaunchedEffect(Unit) {
        cargarProducciones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = { onBack() }
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Text(
                text = "Producción",
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Registrar información de producción",
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = referencia,
            onValueChange = { referencia = it },
            label = { Text("Código de referencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = materiales,
            onValueChange = { materiales = it },
            label = { Text("Materiales") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = tiempoConfeccion,
            onValueChange = { tiempoConfeccion = it },
            label = { Text("Tiempo de confección (horas)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (
                    referencia.isBlank() ||
                    materiales.isBlank() ||
                    tiempoConfeccion.isBlank()
                ) {

                    mensaje = "Completa todos los campos"

                } else {

                    val tiempo = tiempoConfeccion.toDoubleOrNull()

                    if (tiempo == null || tiempo < 0) {

                        mensaje = "El tiempo debe ser un número válido"

                    } else {

                        scope.launch {

                            guardando = true
                            mensaje = ""

                            try {

                                val produccion = Produccion(
                                    codigoReferencia = referencia,
                                    materiales = materiales,
                                    tiempoConfeccion = tiempo
                                )

                                val respuesta =
                                    RetrofitClient.produccionApi.crearProduccion(
                                        produccion
                                    )

                                if (respuesta.isSuccessful) {

                                    mensaje =
                                        "Producción guardada correctamente"

                                    referencia = ""
                                    materiales = ""
                                    tiempoConfeccion = ""

                                    cargarProducciones()

                                } else {

                                    mensaje =
                                        "Error al guardar. Código: ${respuesta.code()}"
                                }

                            } catch (e: Exception) {

                                mensaje =
                                    "No se pudo conectar con el servidor"

                            } finally {

                                guardando = false
                            }
                        }
                    }
                }
            },
            enabled = !guardando,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = if (guardando) {
                    "Guardando..."
                } else {
                    "Guardar producción"
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mensaje.isNotEmpty()) {

            Text(
                text = mensaje,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Producciones registradas",
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                cargarProducciones()
            },
            enabled = !cargando,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = if (cargando) {
                    "Cargando..."
                } else {
                    "Actualizar lista"
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (producciones.isEmpty()) {

            Text(
                text = "No hay producciones registradas.",
                fontSize = 15.sp
            )

        } else {

            producciones.forEach { produccion ->

                Text(
                    text = "Código: ${produccion.codigoReferencia}",
                    fontSize = 16.sp
                )

                Text(
                    text = "Materiales: ${produccion.materiales}",
                    fontSize = 16.sp
                )

                Text(
                    text = "Tiempo: ${produccion.tiempoConfeccion} horas",
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}