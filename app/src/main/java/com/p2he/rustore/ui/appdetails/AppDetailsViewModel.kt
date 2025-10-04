package com.p2he.rustore.ui.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Определяем состояния UI для экрана деталей
sealed interface AppDetailsUiState {
    data class Success(val app: AppModel) : AppDetailsUiState
    object Loading : AppDetailsUiState
    object Error : AppDetailsUiState
}

class AppDetailsViewModel : ViewModel() {

    private val appRepository = AppRepository()

    private val _uiState = MutableStateFlow<AppDetailsUiState>(AppDetailsUiState.Loading)
    val uiState: StateFlow<AppDetailsUiState> = _uiState

    fun loadApp(appId: String) {
        viewModelScope.launch {
            _uiState.value = AppDetailsUiState.Loading
            try {
                val app = appRepository.getAppById(appId)
                if (app != null) {
                    _uiState.value = AppDetailsUiState.Success(app)
                } else {
                    _uiState.value = AppDetailsUiState.Error
                }
            } catch (e: Exception) {
                _uiState.value = AppDetailsUiState.Error
            }
        }
    }
}
