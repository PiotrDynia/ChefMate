package com.example.chefmate.featureHome.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResult
import com.example.chefmate.featureHome.domain.repository.HomeRepository

class HomeRepositoryImpl(private val apiService: APIService) : HomeRepository {

    override suspend fun getRecipes(
        query: String,
        sortStrategy: String,
        cuisines: String,
        diets: String,
        intolerances: String,
        resultsCount: Int
    ): GetRecipeResult {
        return apiService.getRecipes(
            query = query,
            sortStrategy = sortStrategy,
            cuisines = cuisines,
            diets = diets,
            intolerances = intolerances,
            resultsCount = resultsCount
        )
    }

    override suspend fun getRandomRecipes(resultsCount: Int): GetRandomRecipeResult {
        return apiService.getRandomRecipes(resultsCount = resultsCount)
    }

    override suspend fun getAutocompleteRecipes(query: String, resultsCount: Int): GetRecipesAutocompleteResult {
        return apiService.getAutocompleteRecipes(query = query, resultsCount = resultsCount)
    }
}