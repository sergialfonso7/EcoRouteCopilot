package com.ecoroute.ecoroute

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecoroute.ecoroute.ui.theme.EcoRouteTheme
import com.ecoroute.ecoroute.ui.theme.GreenECO
import com.ecoroute.ecoroute.ui.theme.TypeFont

@Composable
fun AddVehicleView (
   // navController: NavController
) {
    Column {
        Row{
            HeaderAddVehicle()
        }
        Row {
            ParametersAddVehicle()
        }

    }
}

val colors = listOf("Rojo", "Azul", "Verde", "Negro", "Blanco")
@Composable
private fun ParametersAddVehicle() {
    // Estado de los campos de entrada
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var año by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(colors[0]) } // Color seleccionado inicialmente
    var consumo by remember { mutableStateOf("") }
    var autonomia by remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }


    // Almacenar el nuevo coche en la base de datos
    fun guardarCoche() {
        // Aquí puedes implementar la lógica para guardar el coche en la base de datos
        // utilizando la información que se encuentra en los campos de entrada y en el estado del color
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        LabeledTextField(
            label = "Marca",
            value = marca,
            title = "Introduce la marca del vehículo",
            onValueChange = { marca = it }
        )

        LabeledTextField(
            label = "Modelo",
            value = modelo,
            title = "Introduce el modelo del vehículo",
            onValueChange = { modelo = it }
        )

        LabeledTextField(
            label = "Año",
            value = año,
            title = "Introduce el año del vehículo",
            onValueChange = { año = it }
        )

        ColorSelector(color = color, onColorSelected = { newColor -> color = newColor })

        LabeledTextField(
            label = "Consumo medio",
            value = consumo,
            title = "Introduce el consumo medio del vehículo",
            onValueChange = { consumo = it }
        )
        LabeledTextField(
            label = "Autonomía máxima (km.)",
            value = autonomia,
            title = "Introduce la autonomía máxima del vehículo",
            onValueChange = { autonomia = it }
        )
        Row(
            Modifier
                .padding(16.dp)
        ) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it },
                modifier = Modifier
                    .padding(end = 16.dp)
            )
            Text(text = "Vehículo adaptado para discapacitados")
        }

        // Botón para guardar el nuevo coche en la base de datos
        Button(
            onClick = { guardarCoche() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GreenECO,
                contentColor = androidx.compose.ui.graphics.Color.White
            )
        ) {
            Text("Guardar coche",
            style = TypeFont.body1
            )
        }
    }
}

@Composable
private fun ColorSelector(
    color: String,
    onColorSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Color: ", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = {
                        expanded = true
                    })
                    .background(Color.LightGray)

            ) {
                Text(color, modifier = Modifier.padding(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    colors.forEach { colorItem ->
                        DropdownMenuItem(
                            onClick = {
                                onColorSelected(colorItem)
                                expanded = false
                            }
                        ) {
                            Text(colorItem)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun LabeledTextField(
    label: String,
    value: String,
    title: String,
    onValueChange: (String) -> Unit
) {
    Column() {
        Text(text = label)
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(title) }
        )
    }
}

@Composable
private fun HeaderAddVehicle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back image",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { /* Anar a la pestanya de cotxes de l'usuari */ }
            )
        }
        Column(
            modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Añadir vehículo",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "Remove image",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { /* Cridar funció eliminar cotxe */ }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    EcoRouteTheme {
        AddVehicleView()
    }
}