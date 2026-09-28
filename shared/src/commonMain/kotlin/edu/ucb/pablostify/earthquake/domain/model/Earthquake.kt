package edu.ucb.pablostify.earthquake.domain.model

data class Earthquake(
    val place: String,
    val magnitude: Double,
    val time: Long,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)