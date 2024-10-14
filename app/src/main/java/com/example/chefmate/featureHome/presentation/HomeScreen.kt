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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.util.LoadingScreen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureHome.presentation.components.DietaryPreferencesRows
import com.example.chefmate.featureHome.presentation.components.RecommendationsRow
import com.example.chefmate.featureHome.presentation.components.SearchSection
import com.example.chefmate.featureHome.presentation.components.TopWelcomeRow
import com.example.chefmate.featureSearch.presentation.SearchEvent
import com.example.chefmate.featureSearch.presentation.SearchState
import com.example.chefmate.featureSearch.presentation.SearchViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    // TODO delete and move the logic to data store or figure this shit out some other way
                    val latestState = viewModel.state.value
                    sharedViewModel.onEvent(SearchEvent.OnHomeScreenSearchClick(
                        SearchState(
                            searchInput = latestState.searchInput,
                            selectedCuisines = latestState.selectedCuisines.map { it.displayName }.toSet(),
                            selectedDiets = latestState.selectedDiets.map { it.displayName }.toSet(),
                            selectedIntolerances = latestState.selectedIntolerances.map { it.displayName }.toSet(),
                            selectedMealTypes = latestState.selectedMealTypes.map { it.displayName }.toSet(),
                        )
                    ))
                    navController.navigateTo(event.route)
                }
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
                onEvent = viewModel::onEvent
            )
            Spacer(modifier = Modifier.height(16.dp))
            DietaryPreferencesRows(
                state = state,
                onEvent = viewModel::onEvent
            )
            RecommendationsRow(state = state)
        }
    }
}