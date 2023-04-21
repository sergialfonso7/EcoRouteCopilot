package com.ecoroute.ecoroute

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.constraintlayout.compose.DesignElements.map
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.ecoroute.ecoroute.ui.theme.EcoRouteTheme
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.GeoApiContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private var map: GoogleMap? by mutableStateOf(null)
    private lateinit var geoApiContext: GeoApiContext

    private var originAddress by mutableStateOf(TextFieldValue())
    private var destinationAddress by mutableStateOf(TextFieldValue())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = MapView(this)
        mapView.onCreate(savedInstanceState)
        geoApiContext = GeoApiContext.Builder()
            .apiKey(getString(R.string.google_maps_api_key))
            .build()

        mapView.getMapAsync(this)

        setContent {
            EcoRouteTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        MyApp()
                    }
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginView(navController = navController)
            }

            composable("register") {
                RegisterView(navController = navController)
            }

            composable("profile") {
                ProfileView()
            }

            composable("Routes") {
                map?.let { map ->
                    RoutesActivity(
                        originAddress = originAddress,
                        destinationAddress = destinationAddress,
                        onOriginAddressChange = { newValue ->
                            originAddress = newValue
                        },
                        onDestinationAddressChange = { newValue ->
                            destinationAddress = newValue
                        },
                        map = map,
                        geoApiContext = geoApiContext,
                        navController = navController
                    )
                }
            }

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val barcelona = LatLng(41.386942934391996, 2.1700953772971987)
        map!!.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(barcelona))

        map!!.setOnMapLoadedCallback(object : GoogleMap.OnMapLoadedCallback {
            override fun onMapLoaded() {
                map!!.setOnCameraMoveStartedListener { reason ->
                    if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                        val boundsBuilder = LatLngBounds.Builder()
                        map!!.projection?.visibleRegion?.latLngBounds?.let { visibleRegion ->
                            boundsBuilder.include(visibleRegion.northeast)
                            boundsBuilder.include(visibleRegion.southwest)
                            val bounds = boundsBuilder.build()
                            val south = bounds.southwest.latitude
                            val north = bounds.northeast.latitude
                            val west = bounds.southwest.longitude
                            val east = bounds.northeast.longitude

                            //Cridar a la funció del backend per demanar tots els punts de recàrrega

                            Log.i("South: $south", "North: $north")
                            Log.i("West: $west", "East: $east")
                        }
                    }
                }
            }
        })
    }
}
