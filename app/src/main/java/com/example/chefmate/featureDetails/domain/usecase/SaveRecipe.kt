package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.core.domain.util.imageManager.ImageManager
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class SaveRecipe(
    private val repository: DetailsRepository,
    private val imageManager: ImageManager
) {
    suspend operator fun invoke(recipe: RecipeDetails) {
        val localImagePath = imageManager.cacheImage(recipe.image)

        val recipeEntity = RecipeEntity(
            id = recipe.id,
            title = recipe.title,
            summary = recipe.summary,
            instructions = recipe.instructions,
            servings = recipe.servings,
            readyInMinutes = recipe.readyInMinutes,
            aggregateLikes = recipe.aggregateLikes,
            pricePerServing = recipe.pricePerServing,
            imagePath = localImagePath ?: ""
        )

        val ingredientEntities = recipe.extendedIngredients.map { ingredient ->
            val localIngredientImagePath =
                imageManager.cacheImage("https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}")
            val isIngredientInShoppingCart = repository.isIngredientInShoppingCart(ingredient.id)

            IngredientEntity(
                id = ingredient.id,
                recipeId = recipe.id,
                name = ingredient.name,
                originalName = ingredient.original,
                imagePath = localIngredientImagePath ?: "",
                isInShoppingCart = isIngredientInShoppingCart
            )
        }
        repository.saveRecipeWithIngredients(recipeEntity, ingredientEntities)
    }
}
