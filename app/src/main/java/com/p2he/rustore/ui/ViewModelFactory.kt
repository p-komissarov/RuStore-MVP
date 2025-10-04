package com.p2he.rustore.ui // Make sure this package name is correct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.gallery.GalleryViewModel

// Define a constructor that accepts AppRepository
class ViewModelFactory(private val appRepository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel is GalleryViewModel
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            // If it is, create and return an instance, passing the repository
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(appRepository) as T
        }
        // If it's some other ViewModel, throw an exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
