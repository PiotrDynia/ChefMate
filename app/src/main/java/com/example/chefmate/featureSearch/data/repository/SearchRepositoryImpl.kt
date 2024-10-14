package com.example.chefmate.featureSearch.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
import com.example.chefmate.featureSearch.presentation.SearchState

class SearchRepositoryImpl(private val apiService: APIService) : SearchRepository {
    override suspend fun getRecipes(searchState: SearchState): GetRecipeResult {
        return apiService.getRecipes(
            query = searchState.searchInput,
            diets = searchState.selectedDiets.joinToString(","),
            cuisines = searchState.selectedCuisines.joinToString(","),
            intolerances = searchState.selectedIntolerances.joinToString(","),
            mealTypes = searchState.selectedMealTypes.joinToString(","),
            excludedCuisines = searchState.excludedCuisines.joinToString(","),
            minCalories = searchState.caloriesSliderPosition.start.toInt(),
            maxCalories = searchState.caloriesSliderPosition.endInclusive.toInt(),
            minServings = searchState.servingsSliderPosition.start.toInt(),
            maxServings = searchState.servingsSliderPosition.endInclusive.toInt(),
            sortStrategy = searchState.selectedSortType
        )
    }
}