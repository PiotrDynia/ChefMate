package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class CheckRecipeIsBookmarked(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(id: Int) : Boolean {
        return repository.isRecipeBookmarked(id)
    }
}