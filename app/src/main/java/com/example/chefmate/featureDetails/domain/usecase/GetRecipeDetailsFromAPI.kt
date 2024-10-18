package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class GetRecipeDetailsFromAPI(
    private val repository: DetailsRepository
) {

    suspend operator fun invoke(id: Int) : RecipeDetails {
        return repository.getRecipeDetailsFromAPI(id)
    }
}