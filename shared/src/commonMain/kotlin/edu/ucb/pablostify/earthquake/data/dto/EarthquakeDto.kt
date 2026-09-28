package edu.ucb.pablostify.earthquake.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    val features: List<EarthquakeFeatureDto>
)

@Serializable
data class EarthquakeFeatureDto(
    val properties: EarthquakePropertiesDto,
    val geometry: EarthquakeGeometryDto
)

@Serializable
data class EarthquakePropertiesDto(
    val place: String = "",
    val mag: Double = 0.0,
    val time: Long = 0L,
    val url: String = ""
)

@Serializable
data class EarthquakeGeometryDto(
    val coordinates: List<Double> = emptyList()
)