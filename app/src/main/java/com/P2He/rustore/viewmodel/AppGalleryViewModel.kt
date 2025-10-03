package com.P2He.rustore.viewmodel

import androidx.lifecycle.ViewModel
import com.P2He.rustore.model.AppModel
import com.P2He.rustore.repository.AppRepository

class AppGalleryViewModel : ViewModel() {
    private val repository = AppRepository()
    val apps: List<AppModel> = repository.getApps()
}