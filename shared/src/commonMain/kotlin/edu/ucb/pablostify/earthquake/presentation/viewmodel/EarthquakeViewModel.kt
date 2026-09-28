package edu.ucb.pablostify.earthquake.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucb.pablostify.earthquake.domain.usecase.GetEarthquakesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EarthquakeViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EarthquakeState())
    val state = _state.asStateFlow()

    init {
        loadEarthquakes()
    }

    private fun loadEarthquakes() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            getEarthquakesUseCase().fold(

                onSuccess = { earthquakes ->
                    _state.update {
                        it.copy(
                            earthquakes = earthquakes,
                            isLoading = false
                        )
                    }
                },

                onFailure = { error ->
                    _state.update {
                        it.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
            )
        }
    }
}