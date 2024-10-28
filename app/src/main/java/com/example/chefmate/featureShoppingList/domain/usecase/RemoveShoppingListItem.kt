package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository

class RemoveShoppingListItem(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(id: Int) {
        repository.removeShoppingListItem(id)
    }
}