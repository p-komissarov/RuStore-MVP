package com.p2he.rustore.repository

import com.p2he.rustore.model.AppModel
import kotlin.collections.listOf

class AppRepository {
    fun getApps(): List<AppModel> {
        return listOf(
            AppModel(
                id = "1",
                name = "VK Мессенджер",
                iconUrl = "https://via.placeholder.com/100",
                shortDescription = "Общение в мессенджере",
                longDescription = "Полное описание приложения VK Мессенджер...",
                category = "Социальные сети",
                developer = "VK",
                ageRating = "12+",
                apkUrl = "https://example.com/app1.apk",
                version = "1.0",
                size = "15 MB"
            ),
            AppModel(
                id = "2",
                name = "VK Музыка",
                iconUrl = "https://via.placeholder.com/100",
                shortDescription = "Слушай музыку",
                longDescription = "Полное описание приложения VK Музыка...",
                category = "Музыка",
                developer = "VK",
                ageRating = "6+",
                apkUrl = "https://example.com/app2.apk",
                version = "1.0",
                size = "20 MB"
            ),
            AppModel(
                id = "3",
                name = "VK Видео",
                iconUrl = "https://via.placeholder.com/100",
                shortDescription = "Смотри видео",
                longDescription = "Полное описание приложения VK Видео...",
                category = "Видео",
                developer = "VK",
                ageRating = "16+",
                apkUrl = "https://example.com/app3.apk",
                version = "1.0",
                size = "25 MB"
            )
        )
    }
}