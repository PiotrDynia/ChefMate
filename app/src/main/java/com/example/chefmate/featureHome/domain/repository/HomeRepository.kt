package com.example.chefmate.featureHome.domain.repository

import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.featureHome.domain.util.PreferencesSelection

interface HomeRepository {
    suspend fun getRecipes(preferencesSelection: PreferencesSelection): GetRecipeResult

    suspend fun getRandomRecipes(): GetRandomRecipeResult

    suspend fun getAutocompleteRecipes(query: String): ArrayList<GetRecipesAutocompleteResultItem>
}