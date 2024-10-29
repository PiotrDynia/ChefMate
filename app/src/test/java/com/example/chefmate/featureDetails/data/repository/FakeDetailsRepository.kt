package com.example.chefmate.featureDetails.data.repository

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class FakeDetailsRepository : DetailsRepository {

    val cachedRecipes = mutableListOf<RecipeWithIngredients>()
    private var recipeFromAPI: RecipeDetails? = null

    override suspend fun getRecipeDetailsFromAPI(id: Int): RecipeDetails {
        return recipeFromAPI ?: throw IllegalStateException("Recipe not found in the fake API")
    }

    override suspend fun getRecipeFromCache(id: Int): RecipeWithIngredients {
        return cachedRecipes.find { it.recipe.id == id }
            ?: throw IllegalStateException("Recipe not found in the cache")
    }

    override suspend fun isRecipeBookmarked(recipeId: Int): Boolean {
        return cachedRecipes.any { it.recipe.id == recipeId }
    }

    override suspend fun isIngredientInShoppingList(ingredientId: Int): Boolean {
        return cachedRecipes.any {
            it.ingredients.any { ingredient ->
                ingredient.id == ingredientId && ingredient.isInShoppingList
            }
        }
    }

    override suspend fun saveRecipeWithIngredients(
        recipe: RecipeEntity,
        ingredients: List<IngredientEntity>
    ) {
        cachedRecipes.removeIf { it.recipe.id == recipe.id }
        cachedRecipes.add(
            RecipeWithIngredients(
                recipe = recipe,
                ingredients = ingredients
            )
        )
    }

    override suspend fun deleteRecipeById(recipeId: Int) {
        cachedRecipes.removeIf { it.recipe.id == recipeId }
    }

    fun createFakeRemoteRecipe(recipe: RecipeDetails) {
        this.recipeFromAPI = recipe
    }
}