package com.example.chefmate.featureHome.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.featureHome.domain.repository.HomeRepository

class HomeRepositoryImpl(private val apiService: APIService) : HomeRepository {

    override suspend fun getRecipes(
        cuisines: String,
        diets: String,
        intolerances: String,
        mealTypes: String
    ): GetRecipeResult {
        return apiService.getRecipes(
            cuisines = cuisines,
            diets = diets,
            intolerances = intolerances,
            mealTypes = mealTypes
        )
    }

    override suspend fun getRandomRecipes(): GetRandomRecipeResult {
        return apiService.getRandomRecipes()
    }

    override suspend fun getAutocompleteRecipes(query: String): ArrayList<GetRecipesAutocompleteResultItem> {
        return apiService.getAutocompleteRecipes(query = query)
    }
}