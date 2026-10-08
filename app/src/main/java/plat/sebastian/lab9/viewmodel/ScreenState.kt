package plat.sebastian.lab9.viewmodel

data class ScreenState<T>(
    val isLoading: Boolean = true,
    val data: T? = null,
    val hasError: Boolean = false
)