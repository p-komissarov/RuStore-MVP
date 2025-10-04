package com.P2He.rustore.model

data class AppModel(
    val id: String,
    val name: String,
    val iconUrl: String,
    val longDescription: String,
    val category: String,
    val developer: String,
    val ageRating: String,
    val apkUrl: String,
    val version: String,
    val size: String
)
