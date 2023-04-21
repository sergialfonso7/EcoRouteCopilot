package com.ecoroute.ecoroute

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.ecoroute.ecoroute.ui.routes.MapViewComponent
import com.ecoroute.ecoroute.ui.theme.GreenECO
import com.ecoroute.ecoroute.ui.theme.RedECO
import com.ecoroute.ecoroute.ui.theme.TypeFont

import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat.startActivity

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApiRequest
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.errors.ZeroResultsException
import com.google.maps.model.GeocodingResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutesActivity(
    originAddress: TextFieldValue,
    destinationAddress: TextFieldValue,
    onOriginAddressChange: (TextFieldValue) -> Unit,
    onDestinationAddressChange: (TextFieldValue) -> Unit,
    map: GoogleMap,
    geoApiContext: GeoApiContext,
    navController: NavHostController
) {
    var stops by remember { mutableStateOf(listOf<String>()) }
    var showAddStop by remember { mutableStateOf(false) }
    var newStop by remember { mutableStateOf("") }
    val drawerState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = drawerState,
        drawerGesturesEnabled = false,
        topBar = {


            TopAppBar(
                title = { Text("EcoRoute") },
                navigationIcon = {

                    IconButton(onClick = { coroutineScope.launch { drawerState.drawerState.open() } }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }



                }
            )
        },
        drawerContent = {
            Drawer(

                isDrawerOpen = drawerState.drawerState.isOpen,
                onDrawerClose = { coroutineScope.launch { drawerState.drawerState.close() } },

                content = {

                        Column(modifier = Modifier.fillMaxSize()) {
                            // Text field for origin address
                            OutlinedTextField(
                                value = originAddress,
                                onValueChange = onOriginAddressChange,
                                label = { Text("Origin Address") },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )

                            // Text field for destination address
                            OutlinedTextField(
                                value = destinationAddress,
                                onValueChange = onDestinationAddressChange,
                                label = { Text("Destination Address") },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )
                            Button(
                                onClick = { showAddStop = true },
                                colors = ButtonDefaults.buttonColors(backgroundColor = GreenECO),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .alpha(if (showAddStop) 0f else 1f)
                            ) {
                                Text("+ Agregar parada")
                            }

                            // Text field to add new stop
                            OutlinedTextField(
                                value = newStop,
                                onValueChange = { newStop = it },
                                label = { Text("Ingrese la dirección de la parada") },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .alpha(if (showAddStop) 1f else 0f)
                            )

                            // Button to add new stop
                            Button(
                                onClick = {
                                    stops = stops.toMutableList().also { it.add(newStop) }
                                    newStop = ""
                                    showAddStop = false
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = GreenECO),
                                modifier = Modifier

                                    .fillMaxWidth()
                                    .alpha(if (showAddStop) 1f else 0f)
                            ) {
                                Text("Agregar")
                            }

                            // Show list of stops
                            // Show list of stops
                            Column(
                                modifier = Modifier.weight(1f)
                                //.alpha(if (stops.isEmpty()) Modifier.weight(0f) else Modifier.weight(1f))
                                //.then(if (stops.isEmpty()) Modifier.height(0.dp) else Modifier.height(IntrinsicSize.Max))
                            ) {
                                stops.forEachIndexed { index, stop ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(stop, Modifier.padding(vertical = 5.dp))

                                        Button(
                                            onClick = {
                                                stops = stops.toMutableList().also { it.removeAt(index) }
                                            },
                                            colors = ButtonDefaults.buttonColors(backgroundColor = RedECO),
                                            modifier = Modifier.padding(vertical = 5.dp)
                                        ) {
                                            Text("Eliminar")
                                        }
                                    }
                                }
                            }


                            //Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        map.clear()
                                        drawRouteOnMap(
                                            map,
                                            geoApiContext,
                                            originAddress.text,
                                            destinationAddress.text,
                                            stops
                                        )
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = GreenECO,),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                Text("Buscar ruta")
                            }
                            Button(
                                onClick = {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        map.clear()
                                        stops = listOf("")

                                    }
                                },

                                colors = ButtonDefaults.buttonColors(backgroundColor = RedECO,),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),


                                ) {
                                Text("Eliminar ruta")
                            }
                        }


                }
            )
        },
        content = {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (mapSurface, column) = createRefs()

                Surface(modifier = Modifier
                    .constrainAs(mapSurface) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(column.start)
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxWidth(1f)
                ) {
                    MapViewComponent(
                        originAddress = originAddress,
                        destinationAddress = destinationAddress
                    )
                }
                Button(
                    onClick = {
                        // Crea un Intent que abra la aplicación de Google Maps con las direcciones.
                        val intent = Intent(
                            Intent.ACTION_VIEW).apply {Uri.parse(
                            "https://www.google.com/maps/dir/?api=1&origin=$originAddress&destination=$destinationAddress")  }

                        navController.context.startActivity(intent)

                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(column) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text("Abrir en Google Maps")
                }


            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { coroutineScope.launch { drawerState.drawerState.open() } }) {
                Icon(Icons.Filled.Search, contentDescription = "Buscar ruta")
            }
        },
        floatingActionButtonPosition = FabPosition.End

    )


}
@Composable
fun Drawer(
    isDrawerOpen: Boolean,
    onDrawerClose: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isDrawerOpen) {
        Surface(
            modifier = Modifier
                .width(350.dp)
                .fillMaxHeight()
                .clickable(onClick = onDrawerClose)

        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}

    private suspend fun getLocationFromAddress(context: GeoApiContext, address: String): LatLng {
        return withContext(Dispatchers.IO) {
            val results: Array<GeocodingResult> = GeocodingApi.geocode(context, address).await()
            if (results.isNotEmpty()) {
                val location = results[0].geometry.location
                LatLng(location.lat, location.lng)
            } else {
                // Si no hay resultados, devuelve una ubicación predeterminada o maneja el error de otra manera.
                LatLng(0.0, 0.0) // Puedes cambiar esto por una ubicación predeterminada.
            }
        }
    }


private suspend fun drawRouteOnMap(map: GoogleMap, geoApiContext: GeoApiContext, originAddress: String, destinationAddress: String, stops: List<String>) {
    if (originAddress.isBlank() || destinationAddress.isBlank()) {
        // No hay valores en ambos campos de texto, salir de la función.
        return
    }

    val origin = withContext(Dispatchers.IO) {
        getLocationFromAddress(geoApiContext, originAddress)
    }
    val destination = withContext(Dispatchers.IO) {
        getLocationFromAddress(geoApiContext, destinationAddress)
    }
    Log.i("RoutesActivity", "Origen: $origin")
    Log.i("RoutesActivity", "Destino: $destination")

    CoroutineScope(Dispatchers.Main).launch {
        try {
            val directions = withContext(Dispatchers.IO) {
                var request = DirectionsApiRequest(geoApiContext)
                    .origin("${origin.latitude},${origin.longitude}")
                    .destination("${destination.latitude},${destination.longitude}")

                stops.forEach {
                    if (it.isNotBlank()) {
                        request = request.waypoints("${it.trim()}")
                    }
                }

                request.await()
            }
            val leg = directions.routes[0].legs[0]
            Log.i("RoutesActivity", "Duración: ${leg.duration.humanReadable}, Distancia: ${leg.distance.humanReadable}")

            val polylineOptions = PolylineOptions()
            val pointsList = directions.routes[0].overviewPolyline.decodePath()
            for (point in pointsList) {
                polylineOptions.add(LatLng(point.lat, point.lng))
            }
            map.addPolyline(polylineOptions)

        } catch (e: ZeroResultsException) {
            // Manejar la excepción aquí, por ejemplo, mostrar un mensaje de error al usuario.
            // Puedes reemplazar este bloque con tu propio método para manejar el error.
            Log.e("RoutesActivity", "No se encontró ninguna ruta entre las direcciones proporcionadas.")
        }
    }
}
