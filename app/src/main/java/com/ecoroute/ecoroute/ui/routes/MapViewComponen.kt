package com.ecoroute.ecoroute.ui.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ecoroute.ecoroute.MainActivity
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApiRequest
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun MapViewComponent(
    originAddress: TextFieldValue,
    destinationAddress: TextFieldValue
) {
    val context = LocalContext.current
    val mapView = MapView(context)
    mapView.onCreate(null)

    val lifecycleOwner = LocalLifecycleOwner.current
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            mapView.onResume()
        }

        override fun onPause(owner: LifecycleOwner) {
            mapView.onPause()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            mapView.onDestroy()
        }
    })

    Column(modifier = Modifier
            .fillMaxSize()) {
        AndroidView(factory = { mapView }) { mapView ->
            val mainActivity = context as MainActivity
            mapView.getMapAsync(mainActivity)
        }
    }
}

private fun MapView.getMapAsync(mainActivity: MainActivity) {
    TODO("Not yet implemented")
}


