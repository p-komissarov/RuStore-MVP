package com.p2he.rustore.repository

import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel

class AppRepository {

    private val apps = listOf(
        AppModel(
            id = "1",
            name = "VK Мессенджер",
            iconRes = R.drawable.apps.vkmicon,
            longDescription = "VK Мессенджер — это быстрый и удобный способ оставаться на связи с друзьями и близкими. Обменивайтесь сообщениями, фотографиями, видео и документами.",
            category = "СОЦ. СЕТИ",
            developer = "VK",
            ageRating = "12+",
            apkUrl = "https://example.com/app1.apk",
            version = "1.12.3",
            size = "55 MB",
            screenshots = listOf(
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+1",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+2",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+3",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+4"
            )
        ),
        AppModel(
            id = "2",
            name = "VK Музыка",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=VK+Music",
            longDescription = "Миллионы треков, подкастов и персональные рекомендации. Слушайте музыку онлайн и офлайн без ограничений.",
            category = "МУЗЫКА И АУДИО",
            developer = "VK",
            ageRating = "6+",
            apkUrl = "https://example.com/app2.apk",
            version = "2.5.1",
            size = "80 MB",
            screenshots = listOf(
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+1",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+2",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+3",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+4"
            )
        ),
        AppModel(
            id = "3",
            name = "VK Видео",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=VK+Video",
            longDescription = "Смотрите фильмы, сериалы, мультфильмы и ролики от блогеров. Наслаждайтесь эксклюзивным контентом и прямыми трансляциями.",
            category = "ФОТО И ВИДЕО",
            developer = "VK",
            ageRating = "16+",
            apkUrl = "https://example.com/app3.apk",
            version = "3.2.0",
            size = "95 MB",
            screenshots = listOf(
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+1",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+2",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+3",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+4"
            )
        ),
        AppModel(
            id = "4",
            name = "Госуслуги",
            iconUrl = "https://via.placeholder.com/150/C70077/FFFFFF?Text=GU",
            longDescription = "Приложение Госуслуги — это удобный доступ к государственным услугам в вашем телефоне. Оплачивайте штрафы, записывайтесь к врачу, получайте выписки и справки.",
            category = "ИНСТРУМЕНТЫ",
            developer = "Минцифры России",
            ageRating = "3+",
            apkUrl = "https://example.com/app4.apk",
            version = "4.1.2",
            size = "120 MB",
            screenshots = listOf(
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+1",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+2",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+3",
                "https://via.placeholder.com/300x500/C70077/FFFFFF?Text=Screenshot+4"
            )
        )
    )

    fun getApps(): List<AppModel> {
        return apps
    }

    fun getAppById(id: String): AppModel? {
        return apps.find { it.id == id }
    }

    fun getUniqueCategories(): List<String> {
        return apps.map { it.category }.distinct()
    }

    fun getAppsByCategory(category: String): List<AppModel> {
        return apps.filter { it.category == category }
    }
}
