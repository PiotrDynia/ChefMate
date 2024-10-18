package com.example.chefmate.featureDetails.data.dataSource

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients

@Dao
interface DetailsDao {

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: Int): RecipeWithIngredients?

    @Upsert
    suspend fun upsertRecipe(recipe: RecipeEntity)

    @Upsert
    suspend fun upsertIngredients(ingredients: List<IngredientEntity>)

    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun deleteRecipeById(recipeId: Int)
}