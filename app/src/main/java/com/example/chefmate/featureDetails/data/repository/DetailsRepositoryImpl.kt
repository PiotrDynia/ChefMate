package com.example.chefmate.featureDetails.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.data.dataSource.DetailsDao
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val recipeDao: DetailsDao
) : DetailsRepository {
    override suspend fun getRecipeDetailsFromAPI(id: Int): RecipeDetails {
        return apiService.getRecipeDetails(id = id)
    }

    override suspend fun getRecipeFromCache(id: Int): RecipeWithIngredients {
        return recipeDao.getRecipeById(id)!!
    }

    override suspend fun isRecipeBookmarked(recipeId: Int): Boolean {
        return recipeDao.getRecipeById(recipeId) != null
    }

    override suspend fun isIngredientInShoppingCart(ingredientId: Int): Boolean {
        return recipeDao.getIngredientFromShoppingCartById(ingredientId) != null
    }

    override suspend fun saveRecipeWithIngredients(recipe: RecipeEntity, ingredients: List<IngredientEntity>) {
        recipeDao.upsertRecipe(recipe)
        recipeDao.upsertIngredients(ingredients)
    }

    override suspend fun deleteRecipeById(recipeId: Int) {
        recipeDao.deleteIngredientsNotInShoppingCart(recipeId)
        recipeDao.deleteRecipeById(recipeId)
    }
}