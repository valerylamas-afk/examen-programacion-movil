package edu.ucb.pablostify.earthquake.data

import edu.ucb.pablostify.earthquake.data.dto.EarthquakeResponseDto
import edu.ucb.pablostify.earthquake.domain.model.Earthquake
import edu.ucb.pablostify.earthquake.domain.repository.EarthquakeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class EarthquakeRepositoryImpl : EarthquakeRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    override suspend fun getEarthquakes(): Result<List<Earthquake>> {
        return try {

            val response = client.get(
                "https://earthquake.usgs.gov/fdsnws/event/1/query" +
                        "?format=geojson" +
                        "&minmagnitude=5" +
                        "&limit=3"
            )

            val data = response.body<EarthquakeResponseDto>()

            val earthquakes = data.features.map { feature ->

                Earthquake(
                    place = feature.properties.place,
                    magnitude = feature.properties.mag,
                    time = feature.properties.time,
                    url = feature.properties.url,
                    longitude = feature.geometry.coordinates.getOrElse(0) { 0.0 },
                    latitude = feature.geometry.coordinates.getOrElse(1) { 0.0 },
                    depth = feature.geometry.coordinates.getOrElse(2) { 0.0 }
                )
            }

            Result.success(earthquakes)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}