package com.p2he.rustore.repository

import com.p2he.rustore.model.AppModel

class AppRepository {

    private val apps = listOf(
        AppModel(
            id = "1",
            name = "VK Мессенджер",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=VKM",
            longDescription = "VK Мессенджер — это быстрый и удобный способ оставаться на связи с друзьями и близкими. Обменивайтесь сообщениями, фотографиями, видео и документами.",
            category = "Социальные сети",
            developer = "VK",
            ageRating = "12+",
            apkUrl = "https://example.com/app1.apk",
            version = "1.12.3",
            size = "55 MB"
        ),
        AppModel(
            id = "2",
            name = "VK Музыка",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=VK+Music",
            longDescription = "Миллионы треков, подкастов и персональные рекомендации. Слушайте музыку онлайн и офлайн без ограничений.",
            category = "Музыка",
            developer = "VK",
            ageRating = "6+",
            apkUrl = "https://example.com/app2.apk",
            version = "2.5.1",
            size = "80 MB"
        ),
        AppModel(
            id = "3",
            name = "VK Видео",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=VK+Video",
            longDescription = "Смотрите фильмы, сериалы, мультфильмы и ролики от блогеров. Наслаждайтесь эксклюзивным контентом и прямыми трансляциями.",
            category = "Видео",
            developer = "VK",
            ageRating = "16+",
            apkUrl = "https://example.com/app3.apk",
            version = "3.2.0",
            size = "95 MB"
        )
    )

    fun getApps(): List<AppModel> {
        return apps
    }

    fun getAppById(id: String): AppModel? {
        return apps.find { it.id == id }
    }
}
