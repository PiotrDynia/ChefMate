package com.example.chefmate.featureDetails.domain.repository

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients

interface DetailsRepository {
    suspend fun getRecipeDetailsFromAPI(id: Int): RecipeDetails
    suspend fun getRecipeFromCache(id: Int) : RecipeWithIngredients
    suspend fun isRecipeBookmarked(recipeId: Int): Boolean
    suspend fun isIngredientInShoppingList(ingredientId: Int): Boolean
    suspend fun saveRecipeWithIngredients(recipe: RecipeEntity, ingredients: List<IngredientEntity>)
    suspend fun deleteRecipeById(recipeId: Int)
}