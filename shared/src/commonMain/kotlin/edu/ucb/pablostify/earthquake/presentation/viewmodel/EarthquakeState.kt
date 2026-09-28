package edu.ucb.pablostify.earthquake.presentation.viewmodel

import edu.ucb.pablostify.earthquake.domain.model.Earthquake

data class EarthquakeState(
    val earthquakes: List<Earthquake> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
