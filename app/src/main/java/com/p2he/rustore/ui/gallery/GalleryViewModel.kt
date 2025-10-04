package com.p2he.rustore.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Определяем состояния UI для экрана галереи
sealed interface GalleryUiState {
    data class Success(val apps: List<AppModel>) : GalleryUiState
    object Loading : GalleryUiState
    object Error : GalleryUiState
}

class GalleryViewModel(private val repository: AppRepository) : ViewModel() {
    // Приватное, изменяемое состояние
    private val _uiState = MutableStateFlow<GalleryUiState>(GalleryUiState.Loading)
    // Публичное, только для чтения состояние, на которое подпишется UI
    val uiState: StateFlow<GalleryUiState> = _uiState

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.value = GalleryUiState.Loading
            try {
                // ИСПРАВЛЕНО: Теперь используется репозиторий из конструктора
                val apps = repository.getApps()
                _uiState.value = GalleryUiState.Success(apps)
            } catch (e: Exception) {
                // В случае ошибки
                _uiState.value = GalleryUiState.Error
            }
        }
    }
}
