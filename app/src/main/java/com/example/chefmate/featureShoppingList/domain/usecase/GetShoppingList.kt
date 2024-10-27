package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class GetShoppingList(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(): Flow<List<IngredientEntity>> {
        return repository.getShoppingList()
    }
}