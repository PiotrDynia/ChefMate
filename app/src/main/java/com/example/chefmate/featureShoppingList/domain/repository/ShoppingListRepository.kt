package com.example.chefmate.featureShoppingList.domain.repository

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    suspend fun getShoppingList(): Flow<List<IngredientEntity>>
    suspend fun isIngredientBookmarked(id: Int) : Boolean
    suspend fun addShoppingListItem(item: IngredientEntity)
    suspend fun addBookmarkedIngredientToShoppingList(id: Int)
    suspend fun removeShoppingListItem(id: Int)
}