package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class GetRecipeFromCache(
    private val repository: DetailsRepository
) {

    suspend operator fun invoke(id: Int) : RecipeWithIngredients {
        return repository.getRecipeFromCache(id)
    }
}