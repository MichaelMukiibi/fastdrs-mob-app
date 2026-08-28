package com.campmap.fastdrs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campmap.fastdrs.feature.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onStartScreening = {
                navController.navigate(Screen.ScreeningSetup.route)
            })
        }
        // Placeholder screens for flow completion
        composable(Screen.ScreeningSetup.route) { /* TODO */ }
        composable(Screen.ImageAcquisition.route) { /* TODO */ }
        composable(Screen.ImageReview.route) { /* TODO */ }
        composable(Screen.Analysis.route) { /* TODO */ }
        composable(Screen.Result.route) { /* TODO */ }
    }
}
