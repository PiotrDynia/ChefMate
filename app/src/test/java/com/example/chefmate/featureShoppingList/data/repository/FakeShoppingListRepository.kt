package com.example.chefmate.featureShoppingList.data.repository

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeShoppingListRepository : ShoppingListRepository {

    val shoppingList = mutableListOf<IngredientEntity>()

    override suspend fun getShoppingList(): Flow<List<IngredientEntity>> {
        return flow { emit(shoppingList) }
    }

    override suspend fun isIngredientBookmarked(id: Int): Boolean {
        return shoppingList.any { it.id == id && it.isBookmarked }
    }

    override suspend fun addShoppingListItem(item: IngredientEntity) {
        shoppingList.add(item)
    }

    override suspend fun addBookmarkedIngredientToShoppingList(id: Int) {
        shoppingList.add(IngredientEntity(
            id = id,
            recipeId = 0,
            name = "",
            originalName = "",
            imagePath = "",
            isBookmarked = true,
            isInShoppingList = true
        ))
    }

    override suspend fun removeShoppingListItem(id: Int) {
        shoppingList.removeIf { it.id == id }
    }
}