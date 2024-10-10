package com.example.chefmate.featureSearch.domain.repository

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection

interface SearchRepository {
    suspend fun getRecipes(searchFilters: SearchFilterSelection): GetRecipeResult
}