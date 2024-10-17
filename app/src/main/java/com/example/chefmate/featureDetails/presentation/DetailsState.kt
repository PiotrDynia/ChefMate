package com.example.chefmate.featureDetails.presentation

import com.example.chefmate.core.data.api.dto.RecipeDetails

data class DetailsState(
    val details: RecipeDetails? = null,
    val isLoading: Boolean = true,
    val isBookmarked: Boolean = false
)