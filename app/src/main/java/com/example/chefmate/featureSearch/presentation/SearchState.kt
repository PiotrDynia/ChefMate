package com.example.chefmate.featureSearch.presentation

import com.example.chefmate.core.domain.util.userPreferences.SortType

data class SearchState(
    val searchInput: String = "",
    val caloriesSliderPosition: ClosedFloatingPointRange<Float> = 0.0f..2000.0f,
    val caloriesSliderRange: ClosedFloatingPointRange<Float> = 0.0f..2000.0f,
    val servingsSliderPosition: ClosedFloatingPointRange<Float> = 1.0f..10.0f,
    val servingsSliderRange: ClosedFloatingPointRange<Float> = 1.0f..10.0f,
    val selectedCuisines: Set<String> = emptySet(),
    val excludedCuisines: Set<String> = emptySet(),
    val selectedDiets: Set<String> = emptySet(),
    val selectedIntolerances: Set<String> = emptySet(),
    val selectedMealTypes: Set<String> = emptySet(),
    val selectedSortType: String = SortType.POPULARITY.sortName
)