package com.p2he.rustore.model

import androidx.annotation.DrawableRes

data class AppModel(
    val id: String,
    val name: String,
    val iconUrl: String? = null,
    @DrawableRes val iconRes: Int? = null,
    val longDescription: String,
    val category: String,
    val developer: String,
    val ageRating: String,
    val apkUrl: String,
    val version: String,
    val size: String,
    val screenshots: List<Any>
)
