package com.example.chefmate.featureDetails.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class DetailsRepositoryImpl(private val apiService: APIService) : DetailsRepository {
    override suspend fun getRecipeDetails(id: Int): RecipeDetails {
        return apiService.getRecipeDetails(id = id)
    }
}