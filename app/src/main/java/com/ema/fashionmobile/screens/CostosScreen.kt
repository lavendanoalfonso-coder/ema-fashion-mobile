package com.ema.fashionmobile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CostosScreen(
    onBack: () -> Unit
) {

    var referencia by remember {
        mutableStateOf("")
    }

    var materiales by remember {
        mutableStateOf("")
    }

    var manoObra by remember {
        mutableStateOf("")
    }

    var otrosCostos by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = {
                    onBack()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Text(
                text = "Costos",
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Evaluar el costo de producción de la prenda",
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = referencia,
            onValueChange = {
                referencia = it
            },
            label = {
                Text("Código de referencia")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = materiales,
            onValueChange = {
                materiales = it
            },
            label = {
                Text("Costo de materiales")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = manoObra,
            onValueChange = {
                manoObra = it
            },
            label = {
                Text("Costo de mano de obra")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = otrosCostos,
            onValueChange = {
                otrosCostos = it
            },
            label = {
                Text("Otros costos")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                // Próximamente se calculará y guardará el costo.
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Calcular costo"
            )
        }
    }
}