package com.campmap.fastdrs.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ScreeningSetup : Screen("screening_setup")
    object ImageAcquisition : Screen("image_acquisition")
    object ImageReview : Screen("image_review")
    object Analysis : Screen("analysis")
    object Result : Screen("result")
}
