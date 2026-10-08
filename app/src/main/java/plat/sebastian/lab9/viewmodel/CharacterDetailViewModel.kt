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
import plat.sebastian.lab9.Character
import plat.sebastian.lab9.CharacterDb
import plat.sebastian.lab9.screens.main.CharacterDetailDestination

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId =
        savedStateHandle.toRoute<CharacterDetailDestination>().id

    private val db = CharacterDb()

    private val _uiState =
        MutableStateFlow(ScreenState<Character>())

    val uiState = _uiState.asStateFlow()

    private var loadingJob: Job? = null

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        loadingJob?.cancel()

        _uiState.value = ScreenState(isLoading = true)

        loadingJob = viewModelScope.launch {
            delay(2000)

            try {
                val character = db.getCharacterById(characterId)

                _uiState.value = ScreenState(
                    isLoading = false,
                    data = character
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