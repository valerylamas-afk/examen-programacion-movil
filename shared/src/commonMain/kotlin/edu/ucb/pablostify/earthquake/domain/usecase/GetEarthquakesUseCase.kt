package edu.ucb.pablostify.earthquake.domain.usecase

import edu.ucb.pablostify.earthquake.domain.model.Earthquake
import edu.ucb.pablostify.earthquake.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(
    private val repository: EarthquakeRepository
) {

    suspend operator fun invoke(): Result<List<Earthquake>> {
        return repository.getEarthquakes()
    }
}