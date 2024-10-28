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
        dao.upsertIngredient(item)
    }

    override suspend fun addBookmarkedIngredientToShoppingCart(id: Int) {
        dao.addBookmarkedIngredientToShoppingCart(id)
    }

    override suspend fun removeShoppingListItem(id: Int) {
        if (isIngredientBookmarked(id)) {
            dao.removeBookmarkedIngredientFromShoppingCart(id)
        } else {
            dao.removeIngredientById(id)
        }
    }
}