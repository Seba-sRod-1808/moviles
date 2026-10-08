package plat.sebastian.lab9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.sebastian.lab9.Location
import plat.sebastian.lab9.LocationDb

class LocationsViewModel : ViewModel() {

    private val db = LocationDb()

    private val _uiState =
        MutableStateFlow(ScreenState<List<Location>>())

    val uiState = _uiState.asStateFlow()

    private var loadingJob: Job? = null

    init {
        loadLocations()
    }

    fun loadLocations() {
        loadingJob?.cancel()

        _uiState.value = ScreenState(isLoading = true)

        loadingJob = viewModelScope.launch {
            delay(4000)

            try {
                val locations = db.getAllLocations()

                _uiState.value = ScreenState(
                    isLoading = false,
                    data = locations
                )
            } catch (e: Exception) {
                _uiState.value = ScreenState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun showError() {
        loadingJob?.cancel()

        _uiState.value = ScreenState(
            isLoading = false,
            hasError = true
        )
    }
}