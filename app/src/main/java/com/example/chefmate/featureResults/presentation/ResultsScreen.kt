package com.example.chefmate.featureResults.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chefmate.core.domain.util.navigation.navigateTo
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureResults.presentation.components.FilterSearchBar
import com.example.chefmate.featureResults.presentation.components.ResultsContent
import com.example.chefmate.featureSearch.presentation.SearchViewModel

@Composable
fun ResultsScreen(
    navController: NavHostController,
    sharedViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val searchState by sharedViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigateTo(event.route)
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilterSearchBar(
            searchState = searchState,
            filterSets = viewModel.getFilterSets(searchState),
            onSearchFilterEvent = sharedViewModel::onEvent,
            onFilterChipClick = { viewModel.onFilterChipClick() }
        )
        ResultsContent(
            results = searchState.searchResults,
            onRecipeClick = { id -> viewModel.onRecipeClick(id) }
        )
    }
}