package com.p2he.rustore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.p2he.rustore.ui.appdetails.AppDetailsScreen
import com.p2he.rustore.ui.categories.CategoriesScreen
import com.p2he.rustore.ui.gallery.GalleryScreen
import com.p2he.rustore.ui.theme.RuStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RuStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Настройка графа навигации
                    NavHost(navController = navController, startDestination = "gallery") {
                        // Экран-галерея
                        composable("gallery") {
                            GalleryScreen(navController = navController)
                        }

                        // Экран категорий
                        composable("categories") {
                            CategoriesScreen(navController = navController)
                        }

                        // Экран деталей с обязательным аргументом appId
                        composable(
                            route = "appDetails/{appId}",
                            arguments = listOf(navArgument("appId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            // Извлекаем appId и передаем его на экран
                            val appId = backStackEntry.arguments?.getString("appId")
                            // Если appId не null, показываем экран. Можно добавить обработку ошибки.
                            if (appId != null) {
                                AppDetailsScreen(navController = navController, appId = appId)
                            } else {
                                // Можно показать экран ошибки или вернуться назад
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
