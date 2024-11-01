package com.example.chefmate.core.domain.util.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}