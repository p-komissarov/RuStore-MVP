package com.p2he.rustore.ui.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.p2he.rustore.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferencesRepository = UserPreferencesRepository(application)

    val onboardingCompleted: StateFlow<Boolean> = userPreferencesRepository.onboardingCompleted
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun setOnboardingCompleted(completed: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setOnboardingCompleted(completed)
        }
    }
}
