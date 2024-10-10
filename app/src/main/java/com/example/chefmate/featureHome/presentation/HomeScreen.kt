package com.example.chefmate.featureHome.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.navigation.BottomNavigationViewModel
import com.example.chefmate.core.presentation.util.LoadingScreen
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureHome.presentation.components.DietaryPreferencesRows
import com.example.chefmate.featureHome.presentation.components.RecommendationsRow
import com.example.chefmate.featureHome.presentation.components.SearchSection
import com.example.chefmate.featureHome.presentation.components.TopWelcomeRow

@Composable
fun HomeScreen(
    navController: NavHostController,
    bottomNavigationViewModel: BottomNavigationViewModel,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        bottomNavigationViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigateTo(event.route)
                else -> Unit
            }
        }
    }

    if (state.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopWelcomeRow()
            SearchSection(
                state = state,
                onEvent = homeViewModel::onEvent,
                onAdvancedSearchClick = {
                    bottomNavigationViewModel.onItemClick(
                        index = 1,
                        navRoute = Screen.Search.route
                    ) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DietaryPreferencesRows(
                state = state,
                onEvent = homeViewModel::onEvent
            )
            RecommendationsRow(state = state)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), bottomNavigationViewModel = hiltViewModel())
}