package com.example.chefmate.core.presentation.util

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome_screen")
    data object Home : Screen("home_screen")
    data object Search : Screen("search_screen")
    data object Results : Screen("results_screen")
    data object Details : Screen("details_screen")
}