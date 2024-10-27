package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository

class RemoveShoppingListItem(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(item: IngredientEntity) {
        repository.removeShoppingListItem(item)
    }
}