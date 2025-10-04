package com.p2he.rustore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.p2he.rustore.ui.appdetails.AppDetailsScreen
import com.p2he.rustore.ui.gallery.GalleryScreen
import com.p2he.rustore.ui.onboarding.OnboardingScreen

@Composable
fun RuStoreNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(navController = navController)
        }
        composable("gallery") {
            GalleryScreen(navController = navController)
        }
        composable(
            // 1. Имя в route должно совпадать с именем в navArgument
            route = "appDetails/{appId}",
            arguments = listOf(navArgument("appId") { type = NavType.StringType })
        ) { backStackEntry ->
            // 2. Извлекаем аргумент по правильному имени "appId"
            val appId = backStackEntry.arguments?.getString("appId")

            // 3. Проверяем, что appId не null, и передаем его
            if (appId != null) {
                AppDetailsScreen(navController = navController, appId = appId)
            } else {
                // Если id не найден, можно вернуться назад, чтобы избежать сбоя
                navController.popBackStack()
            }
        }
    }
}
