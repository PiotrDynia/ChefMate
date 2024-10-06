package com.example.chefmate.featureSearch.presentation

import com.example.chefmate.core.domain.util.SortType

data class SearchState(
    val searchInput: String = "",
    val caloriesMin: String = "",
    val caloriesMax: String = "",
    val servingsMin: String = "",
    val servingsMax: String = "",
    val selectedCuisines: Set<String> = emptySet(),
    val excludedCuisines: Set<String> = emptySet(),
    val selectedDiets: Set<String> = emptySet(),
    val selectedIntolerances: Set<String> = emptySet(),
    val selectedMealTypes: Set<String> = emptySet(),
    val selectedSortType: String = SortType.POPULARITY.sortName
)