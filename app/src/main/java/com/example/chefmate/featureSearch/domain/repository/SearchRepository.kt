package com.example.chefmate.featureSearch.domain.repository

import com.example.chefmate.core.data.api.dto.GetRecipeResult

interface SearchRepository {
    suspend fun getRecipes(
        query: String,
        cuisines: String,
        diets: String,
        intolerances: String
    ): GetRecipeResult
}