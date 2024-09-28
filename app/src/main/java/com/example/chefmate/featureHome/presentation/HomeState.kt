package com.example.chefmate.featureHome.presentation

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.RecipeSimple

data class HomeState(
    val searchInput: String = "",
    val isLoading: Boolean = true,
    val areRandomRecipesLoaded: Boolean = false,
    val selectedCuisines: Set<Cuisine> = emptySet(),
    val selectedDiets: Set<Diet> = emptySet(),
    val selectedIntolerances: Set<Intolerance> = emptySet(),
    val recommendations: List<RecipeSimple> = emptyList()
)