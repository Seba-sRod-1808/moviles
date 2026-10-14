package plat.sebastian.lab9.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import plat.sebastian.lab9.data.UserPreferencesRepository

data class SessionState(
    val isChecking: Boolean = true,
    val userName: String? = null,
    val isSaving: Boolean = false,
    val hasError: Boolean = false
)

class SessionViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        UserPreferencesRepository(application.applicationContext)

    private val _uiState = MutableStateFlow(SessionState())

    val uiState: StateFlow<SessionState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.userName.collect { name ->
                    _uiState.value = _uiState.value.copy(
                        isChecking = false,
                        userName = name
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isChecking = false,
                    hasError = true
                )
            }
        }
    }

    fun login(name: String, onSuccess: () -> Unit) {
        val cleanName = name.trim()

        if (cleanName.isBlank() || _uiState.value.isSaving) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isSaving = true,
                hasError = false
            )

            try {
                repository.saveName(cleanName)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hasError = true
                )
            } finally {
                _uiState.value = _uiState.value.copy(
                    isSaving = false
                )
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.clearName()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hasError = true
                )
            }
        }
    }
}