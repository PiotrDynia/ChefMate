package com.example.chefmate.featureSearch.presentation

import com.example.chefmate.core.domain.util.SortType

data class SearchState(
    val searchInput: String = "",
    val caloriesMin: Int = 0,
    val caloriesMax: Int = 2000,
    val servingsMin: Int = 0,
    val servingsMax: Int = 10,
    val caloriesSliderPosition: ClosedFloatingPointRange<Float> = 0.0f..2000.0f,
    val caloriesSliderRange: ClosedFloatingPointRange<Float> = 0.0f..2000.0f,
    val servingsSliderPosition: ClosedFloatingPointRange<Float> = 0.0f..10.0f,
    val servingsSliderRange: ClosedFloatingPointRange<Float> = 0.0f..10.0f,
    val selectedCuisines: Set<String> = emptySet(),
    val excludedCuisines: Set<String> = emptySet(),
    val selectedDiets: Set<String> = emptySet(),
    val selectedIntolerances: Set<String> = emptySet(),
    val selectedMealTypes: Set<String> = emptySet(),
    val selectedSortType: String = SortType.POPULARITY.sortName
)