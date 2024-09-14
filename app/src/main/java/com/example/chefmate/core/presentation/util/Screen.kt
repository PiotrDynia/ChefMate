package com.example.chefmate.core.presentation.util

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome_screen")
    data object Home : Screen("home_screen")
}