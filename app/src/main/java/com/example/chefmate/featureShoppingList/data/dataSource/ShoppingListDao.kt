package com.example.chefmate.featureShoppingList.data.dataSource

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM ingredient WHERE isInShoppingList = 1")
    fun getShoppingList(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM ingredient WHERE id = :ingredientId AND isBookmarked = 1 LIMIT 1")
    suspend fun getBookmarkedIngredientById(ingredientId: Int): IngredientEntity?

    @Query("UPDATE ingredient SET isInShoppingList = 1 WHERE id = :ingredientId")
    suspend fun addBookmarkedIngredientToShoppingList(ingredientId: Int)

    @Query("UPDATE ingredient SET isInShoppingList = 0 WHERE id = :ingredientId")
    suspend fun removeBookmarkedIngredientFromShoppingList(ingredientId: Int)

    @Upsert
    suspend fun upsertIngredient(ingredient: IngredientEntity)

    @Query("DELETE FROM ingredient WHERE id = :ingredientId")
    suspend fun removeIngredientById(ingredientId: Int)
}