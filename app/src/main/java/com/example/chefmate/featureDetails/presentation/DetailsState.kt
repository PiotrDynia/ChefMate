package com.example.chefmate.featureDetails.presentation

import com.example.chefmate.core.data.api.dto.Ingredient
import com.example.chefmate.core.data.api.dto.RecipeDetails
import kotlinx.coroutines.flow.MutableStateFlow

data class DetailsState(
    val details: RecipeDetails? = null,
    val isLoading: Boolean = true,
    val isBookmarked: Boolean = false,
    val ingredientCartStatusMap: MutableMap<Int, MutableStateFlow<Boolean>> = mutableMapOf(),
    val addedIngredient: Ingredient? = null
)