package com.p2he.rustore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.p2he.rustore.ui.about.AboutUsScreen
import com.p2he.rustore.ui.categories.CategoriesScreen
import com.p2he.rustore.ui.details.AppDetailsScreen
import com.p2he.rustore.ui.gallery.GalleryScreen
import com.p2he.rustore.ui.menu.MenuScreen
import com.p2he.rustore.ui.onboarding.OnboardingScreen

@Composable
fun RuStoreNavGraph(startDestination: String = "onboarding") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding") {
            OnboardingScreen(navController = navController)
        }
        composable("gallery") {
            GalleryScreen(navController = navController)
        }
        composable(
            "app_details/{appId}",
            arguments = listOf(navArgument("appId") { type = NavType.StringType })
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")
            AppDetailsScreen(navController = navController, appId = appId)
        }
        composable(
            route = "categories?subCategory={subCategory}",
            arguments = listOf(navArgument("subCategory") { 
                type = NavType.StringType
                nullable = true 
            })
        ) { backStackEntry ->
            val subCategory = backStackEntry.arguments?.getString("subCategory")
            CategoriesScreen(navController = navController, selectedSubCategory = subCategory)
        }
        composable("about") {
            AboutUsScreen(navController = navController)
        }
        composable("menu") {
            MenuScreen(navController = navController)
        }
    }
}
