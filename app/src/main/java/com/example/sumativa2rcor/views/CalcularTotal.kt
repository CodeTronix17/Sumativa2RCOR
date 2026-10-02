package com.example.sumativa2rcor.views
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sumativa2rcor.component.CamposDeTexto

@Composable
fun CalcularTotal() {

    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precioUnitario by remember { mutableStateOf("") }
    var propinaPorcentaje by remember { mutableStateOf("") }

    var totalPagar by remember { mutableStateOf(0.0) }
    var propinaCalculada by remember { mutableStateOf(0.0) }

    var nombreError by remember { mutableStateOf(false) }
    var cantidadError by remember { mutableStateOf(false) }
    var precioError by remember { mutableStateOf(false) }
    var propinaError by remember { mutableStateOf(false) }

    var propinaExcedidaError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.weight(1.0f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Propina", fontSize = 14.sp)
                    Text(text = "$${String.format("%.2f", propinaCalculada)}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
                }
            }

            Card(
                modifier = Modifier.weight(1.0f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Total a Pagar", fontSize = 14.sp)
                    Text(text = "$${String.format("%.2f", totalPagar)}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        CamposDeTexto(
            value = nombre,
            onValueChange = { nombre = it; nombreError = false },
            label = "Nombre del cliente",
            modifier = Modifier.fillMaxWidth(),
            isError = nombreError
        )


        CamposDeTexto(
            value = cantidad,
            onValueChange = { cantidad = it; cantidadError = false },
            label = "Cantidad",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            isError = cantidadError
        )


        CamposDeTexto(
            value = precioUnitario,
            onValueChange = { precioUnitario = it; precioError = false },
            label = "Precio Unitario",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Decimal,
            isError = precioError
        )

        OutlinedTextField(
            value = propinaPorcentaje,
            onValueChange = {
                propinaPorcentaje = it
                propinaError = false

                val porcentaje = it.toDoubleOrNull() ?: 0.0
                propinaExcedidaError = porcentaje > 10.0
            },
            label = { Text("Porcentaje de Propina (Máx 10%)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = propinaError || propinaExcedidaError,
            supportingText = {
                if (propinaError) {
                    Text("Este campo no puede estar vacío")
                } else if (propinaExcedidaError) {
                    Text("La propina no debe exceder el 10%")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                nombreError = nombre.isBlank()
                cantidadError = cantidad.isBlank()
                precioError = precioUnitario.isBlank()
                propinaError = propinaPorcentaje.isBlank()

                if (!nombreError && !cantidadError && !precioError && !propinaError && !propinaExcedidaError) {
                    val cant = cantidad.toDoubleOrNull() ?: 0.0
                    val precio = precioUnitario.toDoubleOrNull() ?: 0.0
                    val porcentajePropina = propinaPorcentaje.toDoubleOrNull() ?: 0.0

                    val subtotal = cant * precio
                    propinaCalculada = subtotal * (porcentajePropina / 100.0)
                    totalPagar = subtotal + propinaCalculada
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Total")
        }

        OutlinedButton(
            onClick = {
                nombre = ""
                cantidad = ""
                precioUnitario = ""
                propinaPorcentaje = ""
                totalPagar = 0.0
                propinaCalculada = 0.0
                nombreError = false
                cantidadError = false
                precioError = false
                propinaError = false
                propinaExcedidaError = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }
    }
}



