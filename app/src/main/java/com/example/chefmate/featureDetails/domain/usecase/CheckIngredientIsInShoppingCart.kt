package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class CheckIngredientIsInShoppingCart(
    private val repository: DetailsRepository
) {

    suspend operator fun invoke(ingredientId: Int): Boolean {
        return repository.isIngredientInShoppingCart(ingredientId)
    }
}