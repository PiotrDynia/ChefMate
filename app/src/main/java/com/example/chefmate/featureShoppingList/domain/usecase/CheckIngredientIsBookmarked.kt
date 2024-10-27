package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository

class CheckIngredientIsBookmarked(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(id: Int): Boolean {
        return repository.isIngredientBookmarked(id)
    }
}