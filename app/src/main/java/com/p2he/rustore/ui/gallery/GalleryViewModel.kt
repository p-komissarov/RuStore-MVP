package com.p2he.rustore.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface GalleryUiState {
    object Loading : GalleryUiState
    data class Success(val apps: List<AppModel>) : GalleryUiState
    data class Error(val message: String) : GalleryUiState
}

class GalleryViewModel : ViewModel() {

    private val repository = AppRepository()
    private val _uiState = MutableStateFlow<GalleryUiState>(GalleryUiState.Loading)
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch {
            _uiState.value = GalleryUiState.Loading
            try {
                _uiState.value = GalleryUiState.Success(repository.getApps())
            } catch (e: Exception) {
                _uiState.value = GalleryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}