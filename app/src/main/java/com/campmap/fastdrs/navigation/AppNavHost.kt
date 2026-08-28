package com.campmap.fastdrs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.campmap.fastdrs.feature.home.HomeScreen
import com.campmap.fastdrs.feature.screening.ScreeningViewModel
import com.campmap.fastdrs.feature.screening.setup.ScreeningSetupScreen
import com.campmap.fastdrs.feature.screening.acquisition.ImageAcquisitionScreen
import com.campmap.fastdrs.feature.screening.review.ImageReviewScreen
import com.campmap.fastdrs.feature.screening.analysis.AnalysisScreen
import com.campmap.fastdrs.feature.screening.result.ResultScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val viewModel: ScreeningViewModel = viewModel(factory = ScreeningViewModel.provideFactory(context))
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
        composable(Screen.ImageAcquisition.route) {
            ImageAcquisitionScreen(onImageSelected = { image ->
                viewModel.updateImage(image)
                navController.navigate(Screen.ImageReview.route)
            })
        }
        composable(Screen.ImageReview.route) {
            val state by viewModel.screeningState.collectAsState()
            state?.image?.let { image ->
                ImageReviewScreen(
                    image = image,
                    onRetake = { navController.popBackStack() },
                    onAnalyze = {
                        viewModel.runAnalysis()
                        navController.navigate(Screen.Analysis.route)
                    }
                )
            }
        }
        composable(Screen.Analysis.route) {
            val state by viewModel.screeningState.collectAsState()
            LaunchedEffect(state?.prediction) {
                if (state?.prediction != null) {
                    navController.navigate(Screen.Result.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                }
            }
            AnalysisScreen()
        }
        composable(Screen.Result.route) {
            val state by viewModel.screeningState.collectAsState()
            state?.prediction?.let { prediction ->
                ResultScreen(prediction = prediction as com.campmap.fastdrs.core.ml.Prediction, onDone = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                })
            }
        }
    }
}
