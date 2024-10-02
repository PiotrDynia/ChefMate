package com.example.chefmate.featureHome.domain.repository

import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResult

interface HomeRepository {
    suspend fun getRecipes(
        query: String = "",
        sortStrategy: String = "popularity",
        cuisines: String = "",
        diets: String = "",
        intolerances: String = "",
        resultsCount: Int = 10
    ): GetRecipeResult

    suspend fun getRandomRecipes(
        resultsCount: Int = 10
    ): GetRandomRecipeResult

    suspend fun getAutocompleteRecipes(
        query: String = "",
        resultsCount: Int = 5
    ): GetRecipesAutocompleteResult
}