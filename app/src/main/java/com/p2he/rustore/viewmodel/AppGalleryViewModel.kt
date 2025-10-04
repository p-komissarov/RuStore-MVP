package com.p2he.rustore.viewmodel

import androidx.lifecycle.ViewModel
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository

class AppGalleryViewModel : ViewModel() {
    private val repository = AppRepository()
    val apps: List<AppModel> = repository.getApps()
}