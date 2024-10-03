package com.example.chefmate.featureSearch.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection

class SearchRepositoryImpl(private val apiService: APIService) : SearchRepository {
    override suspend fun getRecipes(searchFilters: SearchFilterSelection): GetRecipeResult {
        return apiService.getRecipes(
            query = searchFilters.query,
            diets = searchFilters.diets,
            cuisines = searchFilters.cuisines,
            intolerances = searchFilters.intolerances,
            mealTypes = searchFilters.mealType,
            excludedCuisines = searchFilters.excludedCuisines,
            minCalories = searchFilters.minCalories,
            maxCalories = searchFilters.maxCalories,
            minServings = searchFilters.minServings,
            maxServings = searchFilters.maxServings,
            sortStrategy = searchFilters.sort
        )
    }
}