package com.example.chefmate.core.domain.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String) {
    val navGraph = this@navigateTo.graph
    val startDestination = navGraph.findStartDestination()

    this.navigate(route) {
        popUpTo(startDestination.id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}