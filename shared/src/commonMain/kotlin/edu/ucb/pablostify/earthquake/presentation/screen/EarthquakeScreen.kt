package edu.ucb.pablostify.earthquake.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.ucb.pablostify.earthquake.presentation.viewmodel.EarthquakeViewModel
import kotlinx.datetime.Instant
import org.koin.compose.viewmodel.koinViewModel

fun formatEarthquakeTime(time: Long): String {
    return Instant
        .fromEpochMilliseconds(time)
        .toString()
        .replace("T", " ")
        .replace("Z", " UTC")
}

@Composable
fun EarthquakeScreen(
    viewModel: EarthquakeViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {

        Text(
            text = "Terremotos USGS",
            style = MaterialTheme.typography.headlineMedium
        )

        if (state.isLoading) {
            Text("Cargando...")
        }

        state.error?.let {
            Text("Error: $it")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(state.earthquakes) { earthquake ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Ubicación: ${earthquake.place}")

                        Text("Magnitud: ${earthquake.magnitude}")

                        Text(
                            "Fecha y hora: ${
                                formatEarthquakeTime(earthquake.time)
                            }"
                        )

                        Text("Longitud: ${earthquake.longitude}")

                        Text("Latitud: ${earthquake.latitude}")

                        Text("Profundidad: ${earthquake.depth} km")

                        Text("Enlace: ${earthquake.url}")
                    }
                }
            }
        }
    }
}