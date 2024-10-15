package com.example.chefmate.core.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.featureHome.presentation.HomeScreen
import com.example.chefmate.featureOnboarding.presentation.WelcomeScreen
import com.example.chefmate.featureResults.presentation.ResultsScreen
import com.example.chefmate.featureSearch.presentation.SearchScreen
import com.example.chefmate.featureSearch.presentation.SearchViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    startDestination: String,
    modifier: Modifier = Modifier) {
    val sharedSearchViewModel: SearchViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                modifier = modifier
            )
        }
        composable(route = Screen.Search.route) {
            SearchScreen(
                snackbarHostState = snackbarHostState,
                navController = navController,
                sharedViewModel = sharedSearchViewModel,
                modifier = modifier
            )
        }
        composable(route = Screen.Results.route) {
            ResultsScreen(navController = navController, sharedViewModel = sharedSearchViewModel)
        }
    }
}