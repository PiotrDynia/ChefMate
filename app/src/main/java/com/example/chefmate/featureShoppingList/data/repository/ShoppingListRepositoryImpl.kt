package com.example.chefmate.featureShoppingList.data.repository

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.data.dataSource.ShoppingListDao
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class ShoppingListRepositoryImpl(
    private val dao: ShoppingListDao
) : ShoppingListRepository {
    override suspend fun getShoppingList(): Flow<List<IngredientEntity>> {
        return dao.getShoppingList()
    }

    override suspend fun isIngredientBookmarked(id: Int): Boolean {
        return dao.getBookmarkedIngredientById(id) != null
    }

    override suspend fun addShoppingListItem(item: IngredientEntity) {
        if (isIngredientBookmarked(item.id)) {
            dao.addBookmarkedIngredientToShoppingCart(item.id)
        } else {
            dao.upsertIngredient(item)
        }
    }

    override suspend fun removeShoppingListItem(item: IngredientEntity) {
        if (isIngredientBookmarked(item.id)) {
            dao.removeBookmarkedIngredientFromShoppingCart(item.id)
        } else {
            dao.removeIngredient(item)
        }
    }
}