package com.example.chefmate.featureSearch.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.util.SharedSearchViewModel
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import com.example.chefmate.featureSearch.presentation.components.SearchBar
import com.example.chefmate.featureSearch.presentation.components.SearchFilterRows

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
    sharedViewModel: SharedSearchViewModel,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val isSnackbarVisible = snackbarHostState.currentSnackbarData != null

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = context.getString(event.message)
                )
                is UiEvent.Navigate -> {
                    val latestState = viewModel.state.value
                    sharedViewModel.setFilterSelection(
                        SearchFilterSelection(
                            query = latestState.searchInput,
                            cuisines = latestState.selectedCuisines,
                            excludedCuisines = latestState.excludedCuisines,
                            diets = latestState.selectedDiets,
                            intolerances = latestState.selectedIntolerances,
                            mealType = latestState.selectedMealTypes,
                            sort = latestState.selectedSortType,
                            maxCalories = latestState.caloriesSliderPosition.endInclusive.toInt(),
                            minCalories = latestState.caloriesSliderPosition.start.toInt(),
                            maxServings = latestState.servingsSliderPosition.endInclusive.toInt(),
                            minServings = latestState.servingsSliderPosition.start.toInt(),
                        )
                    )
                    navController.navigateTo(event.route)
                }
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = if (isSnackbarVisible) 80.dp else 0.dp)) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            SearchBar(value = state.searchInput, onEvent = viewModel::onEvent)
            SearchFilterRows(state = state, onEvent = viewModel::onEvent)
        }

        Button(
            onClick = { viewModel.onEvent(SearchEvent.OnSearchClick) },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .align(Alignment.BottomCenter)
                .semantics {
                    contentDescription = context.getString(R.string.search_filtered_recipes)
                }
        ) {
            Text(text = stringResource(R.string.search))
        }
    }
}