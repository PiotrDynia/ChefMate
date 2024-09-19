package com.example.chefmate.featureHome.presentation

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance

data class HomeState(
    val searchInput: String = "",
    val isLoading: Boolean = true,
    val selectedCuisines: Set<Cuisine> = emptySet(),
    val selectedDiets: Set<Diet> = emptySet(),
    val selectedIntolerances: Set<Intolerance> = emptySet()
)