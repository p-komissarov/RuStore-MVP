package com.p2he.rustore.repository

import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel

class AppRepository {

    private val apps = listOf(
        AppModel(
            id = "1",
            name = "ВКонтакте: чаты, видео, музыка",
            iconRes = R.drawable.vkicon,
            longDescription = "ВКонтакте — это общение, бесплатные звонки, мессенджер и чат, музыка и видео, игры и мини-приложения для любых задач, десятки миллионов людей и безграничные возможности для развлечений, бизнеса и обмена новостями из любой точки мира.",
            category = "СОЦ. СЕТИ",
            developer = "VK",
            ageRating = "12+",
            apkUrl = "https://example.com/app1.apk",
            version = "8.148",
            size = "131.8 MB",
            screenshots = listOf(
                R.drawable.vkscreen1,
                R.drawable.vkscreen2,
                R.drawable.vkscreen3,
                R.drawable.vkscreen4
            )
        ),
        AppModel(
            id = "2",
            name = "Блок бласт - игры без интернета",
            iconRes = R.drawable.blokicon,
            longDescription = "Блок бласт — это бесконечная головоломка с блоками, где каждое движение имеет значение. Размещайте блоки, собирайте комбо и набирайте очки. Сохраняйте концентрацию, думайте наперёд и узнайте, насколько высоко вы можете подняться!",
            category = "ИГРЫ",
            developer = "Aimi",
            ageRating = "0+",
            apkUrl = "https://example.com/app1.apk",
            version = "1.7",
            size = "65 MB",
            screenshots = listOf(
                R.drawable.bloscreen1,
                R.drawable.blokscreen2,
                R.drawable.blokscreen3,
            )
        ),
        AppModel(
            id = "3",
            name = "Госуслуги",
            iconRes = R.drawable.gosicon,
            longDescription = "Приложение «Госуслуги» — ваш помощник для взаимодействия с ведомствами и государством\n" +
                    "\n" +
                    "В приложении можно оплачивать штрафы и госпошлины, подавать заявления в ведомства, хранить личные документы и предъявлять их в бытовых ситуациях, сканировать товары, управлять согласиями на использование личных и биометрических данных и многое другое",
            category = "ГОС.",
            developer = "Минцифры",
            ageRating = "0+",
            apkUrl = "https://example.com/app1.apk",
            version = "23.2.0.",
            size = "137 MB",
            screenshots = listOf(
                R.drawable.gosscreen1,
                R.drawable.gosscreen2,
                R.drawable.gosscreen3,
                R.drawable.gosscreen4,
            )
        ),
        AppModel(
            id = "4",
            name = "Mail: Почта, Облако, Календарь",
            iconRes = R.drawable.mailicon,
            longDescription = "Почта Mail — ваш быстрый и удобный почтовый клиент.\n" +
                    "\n" +
                    "Добавьте все свои аккаунты и читайте электронную почту с ящиков на Mail, Яндекс, Microsoft Outlook почта, Gmail от Google, Rambler, Yahoo и других сервисов. Отправляйте письма, записывайте дела в рабочий или личный календарь, загружайте фото и документы на диск в Облако Mail. Звонки, новости и погода — всё в одном приложении!",
            category = "ИНСТРУМЕНТЫ",
            developer = "VK",
            ageRating = "0+",
            apkUrl = "https://example.com/app1.apk",
            version = "15.55.",
            size = "179 MB",
            screenshots = listOf(
                R.drawable.mailscreen1,
                R.drawable.mailscreen2,
                R.drawable.mailscreen3,
                R.drawable.mailscreen4,
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
