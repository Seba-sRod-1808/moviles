package plat.sebastian.lab9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.sebastian.lab9.Character
import plat.sebastian.lab9.CharacterDb

class CharactersViewModel : ViewModel() {

    private val db = CharacterDb()

    private val _uiState =
        MutableStateFlow(ScreenState<List<Character>>())

    val uiState = _uiState.asStateFlow()

    private var loadingJob: Job? = null

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        loadingJob?.cancel()

        _uiState.value = ScreenState(isLoading = true)

        loadingJob = viewModelScope.launch {
            delay(4000)

            try {
                val characters = db.getAllCharacters()

                _uiState.value = ScreenState(
                    isLoading = false,
                    data = characters
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