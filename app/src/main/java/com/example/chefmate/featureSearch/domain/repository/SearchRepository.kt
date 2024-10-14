package com.example.chefmate.featureSearch.domain.repository

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.featureSearch.presentation.SearchState

interface SearchRepository {
    suspend fun getRecipes(searchState: SearchState): GetRecipeResult
}