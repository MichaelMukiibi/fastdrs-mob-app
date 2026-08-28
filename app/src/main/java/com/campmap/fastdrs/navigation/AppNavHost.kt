package com.campmap.fastdrs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.campmap.fastdrs.feature.home.HomeScreen
import com.campmap.fastdrs.feature.screening.ScreeningViewModel
import com.campmap.fastdrs.feature.screening.setup.ScreeningSetupScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val viewModel: ScreeningViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onStartScreening = {
                navController.navigate(Screen.ScreeningSetup.route)
            })
        }
        composable(Screen.ScreeningSetup.route) {
            ScreeningSetupScreen(onContinue = { eye ->
                viewModel.startScreening(eye)
                navController.navigate(Screen.ImageAcquisition.route)
            })
        }
        composable(Screen.ImageAcquisition.route) { /* TODO */ }
        composable(Screen.ImageReview.route) { /* TODO */ }
        composable(Screen.Analysis.route) { /* TODO */ }
        composable(Screen.Result.route) { /* TODO */ }
    }
}
