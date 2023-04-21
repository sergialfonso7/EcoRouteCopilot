package com.ecoroute.ecoroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecoroute.ecoroute.ui.theme.EcoRouteTheme

/* Importar las clases necesarias
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.regions.Regions
import com.amazonaws.services.rds.AmazonRDSClient
Dependències
// Obtener las credenciales de acceso de AWS
//val credentialsProvider: AWSCredentialsProvider = AWSMobileClient.getInstance().awsCredentials
// Crear una instancia de AmazonRDSClient
//val rdsClient = AmazonRDSClient(credentialsProvider, Regions.US_EAST_1)
*/
class VehiclesUsuari : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoRouteTheme {
                EcoRouteTheme {
                    Column {
                        CarList()
                    }
                }
            }
        }
    }
}

@Composable
fun UserVehiclesView (
   // navController: NavController
) {
    Column {
        Row{
            HeaderUserVehicles()
        }
        Row {
            CarList()
        }
    }
}


private val dataStyle = TextStyle(
    fontSize = 18.sp,
    color = Color(android.graphics.Color.parseColor("#51AF56"))
)


private data class Car(val matricula: Int, val nombre: String, val marca: String, val modelo: String, val año: Int, val consumo: String, val autonomia: String)
/*
// Clase de repositorio
class CarRepository(private val rdsClient: AmazonRDSClient) {
    fun getAllCarsForUser(userId: Int): List<Car> {
        // Código para obtener los datos de la base de datos de Amazon RDS utilizando el SDK de AWS
        var cars = List<Car>()
        return cars
    }
}
*/



private fun getSampleCars(): List<Car> {
    return listOf(
        Car(1, "Marca 1", "Modelo 1", "A",2022, "8", "1000", ),
        Car( 2, "Marca 2", "Modelo 2", "B",2021, "8", "1000"),
        Car(3, "Marca 3", "Modelo 3", "C",2020, "8", "1000")
    )
}
// Vista de la lista de coches
@Composable
private fun CarList() {
    var selectedCar by remember { mutableStateOf<Car?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    val carList = getSampleCars()

    LazyColumn {
        // Campo de búsqueda
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar coche") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }

    LazyColumn {
        val filteredCars = carList.filter { car -> car.nombre.contains(searchQuery, ignoreCase = true) }

        items(filteredCars.size ) { index ->
            val car = filteredCars[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable() {
                        selectedCar = car
                    },
                backgroundColor = if (selectedCar == car)  Color(0xFFA3D3C3) else MaterialTheme.colors.surface
            ) {
                Row {
                    Row {
                        Text(text = car.nombre, fontSize = 18.sp)
                        if (car == selectedCar) Text(
                            text = "- Seleccionado",
                            modifier = Modifier
                                .padding(start = 12.dp),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
    if (selectedCar != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row() {
                        VehicleData(car = selectedCar!!)
                    }
                }
            }
        }
    }
}


@Composable
private fun VehicleData(car: Car) {
    Column() {
        Text(text = "Datos del vehículo",
            modifier = Modifier
                .background(Color(0xFFA3D3C3))
                .fillMaxWidth(),
            fontSize = 18.sp)
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    data("Matrícula")
                    Text(text = "${car.matricula}")

                    data("Marca")
                    Text(text = "${car.marca}")

                    data("Modelo")
                    Text(text = "${car.modelo}")
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp, start = 16.dp)
                ) {
                    data("Autonomía")
                    Text(text = "${car.autonomia}")

                    data("Consumo")
                    Text(text = "${car.consumo}")

                    data("Color")
                    Text(text = "${car.consumo}")
                }
            }
        }

    }
}

@Composable
private fun HeaderUserVehicles() {
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
                "Vehiculos",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
private fun data(dataText: String) {
    Text(text = dataText,
        style = dataStyle,
        modifier = Modifier
            .padding(bottom = 8.dp, top = 12.dp))
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    EcoRouteTheme {
        UserVehiclesView()
    }
}