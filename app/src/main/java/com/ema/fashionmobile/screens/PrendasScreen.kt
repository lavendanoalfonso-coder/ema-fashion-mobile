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
import com.ema.fashionmobile.model.Prenda
import com.ema.fashionmobile.network.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun PrendasScreen(
    onBack: () -> Unit
) {

    var referencia by remember { mutableStateOf("") }
    var nombrePrenda by remember { mutableStateOf("") }
    var tipoPrenda by remember { mutableStateOf("") }

    var mensaje by remember { mutableStateOf("") }
    var guardando by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    var prendas by remember {
        mutableStateOf<List<Prenda>>(emptyList())
    }

    val scope = rememberCoroutineScope()

    fun cargarPrendas() {

        scope.launch {

            cargando = true

            try {

                val respuesta =
                    RetrofitClient.prendaApi.listarPrendas()

                if (respuesta.isSuccessful) {

                    prendas = respuesta.body() ?: emptyList()

                } else {

                    mensaje =
                        "No se pudieron cargar las prendas. Código: ${respuesta.code()}"
                }

            } catch (e: Exception) {

                mensaje = "No se pudieron cargar las prendas"

            } finally {

                cargando = false
            }
        }
    }

    LaunchedEffect(Unit) {
        cargarPrendas()
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
                text = "Prendas",
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Registrar y consultar referencias",
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
            value = nombrePrenda,
            onValueChange = { nombrePrenda = it },
            label = { Text("Nombre de la prenda") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = tipoPrenda,
            onValueChange = { tipoPrenda = it },
            label = { Text("Tipo de prenda") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (
                    referencia.isBlank() ||
                    nombrePrenda.isBlank() ||
                    tipoPrenda.isBlank()
                ) {

                    mensaje = "Completa todos los campos"

                } else {

                    scope.launch {

                        guardando = true
                        mensaje = ""

                        try {

                            val prenda = Prenda(
                                codigoReferencia = referencia,
                                nombrePrenda = nombrePrenda,
                                tipoPrenda = tipoPrenda
                            )

                            val respuesta =
                                RetrofitClient.prendaApi.crearPrenda(prenda)

                            if (respuesta.isSuccessful) {

                                mensaje =
                                    "Prenda guardada correctamente"

                                referencia = ""
                                nombrePrenda = ""
                                tipoPrenda = ""

                                cargarPrendas()

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
            },
            enabled = !guardando,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = if (guardando) {
                    "Guardando..."
                } else {
                    "Guardar prenda"
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
            text = "Prendas registradas",
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                cargarPrendas()
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

        if (prendas.isEmpty()) {

            Text(
                text = "No hay prendas registradas.",
                fontSize = 15.sp
            )

        } else {

            prendas.forEach { prenda ->

                Text(
                    text = "Código: ${prenda.codigoReferencia}",
                    fontSize = 16.sp
                )

                Text(
                    text = "Nombre: ${prenda.nombrePrenda}",
                    fontSize = 16.sp
                )

                Text(
                    text = "Tipo: ${prenda.tipoPrenda}",
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}