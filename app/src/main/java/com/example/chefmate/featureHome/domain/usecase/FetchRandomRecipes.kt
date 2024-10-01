package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult

class FetchRandomRecipes(
    private val apiService: APIService
) {

    suspend operator fun invoke() : GetRandomRecipeResult {
        return apiService.getRandomRecipes()
    }
}