package com.example.chefmate.featureDetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.util.LoadingScreen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureDetails.presentation.components.BasicRecipeInfoText
import com.example.chefmate.featureDetails.presentation.components.DetailsImage
import com.example.chefmate.featureDetails.presentation.components.IngredientsSection
import com.example.chefmate.featureDetails.presentation.components.InstructionsText
import com.example.chefmate.featureDetails.presentation.components.SummaryText

@Composable
fun DetailsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigateTo(event.route)
                is UiEvent.PopBackStack -> navController.popBackStack()
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
            horizontalAlignment = Alignment.Start
        ) {
            DetailsImage(state = state, onEvent = viewModel::onEvent)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                BasicRecipeInfoText(state = state)
                SummaryText(summary = state.details?.summary)
                IngredientsSection(ingredients = state.details?.extendedIngredients)
                InstructionsText(instructions = state.details?.instructions)
            }
        }
    }
}