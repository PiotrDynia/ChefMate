package com.example.chefmate.featureResults.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chefmate.core.presentation.util.SharedSearchViewModel
import com.example.chefmate.featureResults.presentation.components.FilterSearchBar

@Composable
fun ResultsScreen(
    sharedViewModel: SharedSearchViewModel,
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val filterSelection by sharedViewModel.filterSelection.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        filterSelection?.let { selection ->
            FilterSearchBar(
                filterSelection = selection,
                filterSets = viewModel.getFilterSets(selection)
            )
        }
    }
}