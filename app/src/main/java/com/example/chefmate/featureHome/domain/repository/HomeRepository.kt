package com.example.chefmate.featureHome.domain.repository

import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem

interface HomeRepository {
    suspend fun getRecipes(
        cuisines: String,
        diets: String,
        intolerances: String,
        mealTypes: String
    ): GetRecipeResult

    suspend fun getRandomRecipes(): GetRandomRecipeResult

    suspend fun getAutocompleteRecipes(query: String): ArrayList<GetRecipesAutocompleteResultItem>
}