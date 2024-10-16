package com.example.chefmate.featureDetails.domain.repository

import com.example.chefmate.core.data.api.dto.RecipeDetails

interface DetailsRepository {
    suspend fun getRecipeDetails(id: Int) : RecipeDetails
}