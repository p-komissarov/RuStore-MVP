package com.p2he.rustore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.p2he.rustore.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val startDestination: String? = null
)

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserPreferencesRepository(application)

    val uiState: StateFlow<MainActivityUiState> = repository.onboardingCompleted
        .map { onboardingCompleted ->
            MainActivityUiState(
                isLoading = false,
                startDestination = if (onboardingCompleted) "gallery" else "onboarding"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainActivityUiState(isLoading = true)
        )
}
