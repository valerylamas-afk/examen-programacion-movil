package edu.ucb.pablostify.earthquake.domain.repository

import edu.ucb.pablostify.earthquake.domain.model.Earthquake

interface EarthquakeRepository {
    suspend fun getEarthquakes(): Result<List<Earthquake>>
}