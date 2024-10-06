package com.example.chefmate.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.featureHome.presentation.HomeScreen
import com.example.chefmate.featureOnboarding.presentation.WelcomeScreen
import com.example.chefmate.featureSearch.presentation.SearchScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    bottomNavigationViewModel: BottomNavigationViewModel) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, bottomNavigationViewModel = bottomNavigationViewModel, modifier = modifier)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(modifier = modifier)
        }
    }
}