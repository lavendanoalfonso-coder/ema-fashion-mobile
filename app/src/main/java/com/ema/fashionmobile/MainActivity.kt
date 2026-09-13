package com.ema.fashionmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ema.fashionmobile.screens.ComiteScreen
import com.ema.fashionmobile.screens.CostosScreen
import com.ema.fashionmobile.screens.DisenoScreen
import com.ema.fashionmobile.screens.PatronajeScreen
import com.ema.fashionmobile.screens.PrendasScreen
import com.ema.fashionmobile.screens.ProduccionScreen
import com.ema.fashionmobile.ui.theme.EMAFashionMobileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EMAFashionMobileTheme {
                EMAApp()
            }
        }
    }
}

@Composable
fun EMAApp() {

    var pantallaActual by remember {
        mutableStateOf("inicio")
    }

    when (pantallaActual) {

        "inicio" -> {
            EMAScreen(
                onModuleClick = { modulo ->

                    pantallaActual = modulo
                }
            )
        }

        "diseno" -> {
            DisenoScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }

        "patronaje" -> {
            PatronajeScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }

        "produccion" -> {
            ProduccionScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }

        "costos" -> {
            CostosScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }

        "comite" -> {
            ComiteScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }

        "prendas" -> {
            PrendasScreen(
                onBack = {
                    pantallaActual = "inicio"
                }
            )
        }
    }
}

data class EMAModule(
    val name: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun EMAScreen(
    onModuleClick: (String) -> Unit
) {

    val modules = listOf(

        EMAModule(
            "Diseño",
            "Crear y gestionar diseños",
            Icons.Default.DesignServices,
            "diseno"
        ),

        EMAModule(
            "Patronaje",
            "Medidas y acotaciones",
            Icons.Default.Straighten,
            "patronaje"
        ),

        EMAModule(
            "Producción",
            "Materiales y confección",
            Icons.Default.Factory,
            "produccion"
        ),

        EMAModule(
            "Costos",
            "Calcular costos de producción",
            Icons.Default.MonetizationOn,
            "costos"
        ),

        EMAModule(
            "Comité",
            "Revisar y aprobar prendas",
            Icons.Default.Groups,
            "comite"
        ),

        EMAModule(
            "Prendas",
            "Consultar referencias",
            Icons.Default.Checkroom,
            "prendas"
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F5F3))
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(28.dp))

            Column {

                Text(
                    text = "EMA",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222)
                )

                Text(
                    text = "Fashion Mobile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF77706B)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Bienvenida",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222)
                )

                Text(
                    text = "Gestiona el proceso de desarrollo y producción de moda.",
                    fontSize = 15.sp,
                    color = Color(0xFF77706B),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Módulos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF222222)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(modules) { module ->

                    ModuleCard(
                        module = module,
                        onClick = {
                            onModuleClick(module.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ModuleCard(
    module: EMAModule,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        color = Color(0xFFE9E2DD),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = module.icon,
                    contentDescription = module.name,
                    tint = Color(0xFF5E554F),
                    modifier = Modifier.size(25.dp)
                )
            }

            Column {

                Text(
                    text = module.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222)
                )

                Text(
                    text = module.description,
                    fontSize = 12.sp,
                    color = Color(0xFF77706B),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}