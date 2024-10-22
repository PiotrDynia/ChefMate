package com.example.chefmate.featureBookmarks.data.dataSource

import androidx.room.Query
import androidx.room.Transaction
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

interface BookmarksDao {

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<RecipeWithIngredients>>
}