package plat.sebastian.lab9.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.sebastian.lab9.Location
import plat.sebastian.lab9.LocationDb
import plat.sebastian.lab9.screens.main.LocationDetailDestination

class LocationDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val locationId =
        savedStateHandle.toRoute<LocationDetailDestination>().id

    private val db = LocationDb()

    private val _uiState =
        MutableStateFlow(ScreenState<Location>())

    val uiState = _uiState.asStateFlow()

    private var loadingJob: Job? = null

    init {
        loadLocation()
    }

    fun loadLocation() {
        loadingJob?.cancel()

        _uiState.value = ScreenState(isLoading = true)

        loadingJob = viewModelScope.launch {
            delay(2000)

            try {
                val location = db.getLocationById(locationId)

                _uiState.value = ScreenState(
                    isLoading = false,
                    data = location
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