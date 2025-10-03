package com.P2He.rustore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.P2He.rustore.ui.onboarding.OnboardingScreen
import androidx.compose.material3.Text

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
            Text("Экран витрины - скоро будет готов!")
        }
    }
}