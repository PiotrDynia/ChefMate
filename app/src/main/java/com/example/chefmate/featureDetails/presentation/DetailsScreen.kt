package com.example.chefmate.featureDetails.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chefmate.featureDetails.DetailsViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val recipeDetails by viewModel.recipeDetails.collectAsStateWithLifecycle()

    Text(text = "HELLO")

    recipeDetails?.let {
        println(it)
    }
}