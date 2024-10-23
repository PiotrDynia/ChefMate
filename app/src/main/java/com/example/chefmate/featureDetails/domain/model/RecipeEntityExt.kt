package com.example.chefmate.featureDetails.domain.model

import com.example.chefmate.core.data.api.dto.RecipeDetails

fun RecipeWithIngredients.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(
        id = recipe.id,
        title = recipe.title,
        summary = recipe.summary,
        instructions = recipe.instructions,
        servings = recipe.servings,
        readyInMinutes = recipe.readyInMinutes,
        aggregateLikes = recipe.aggregateLikes,
        image = recipe.imagePath,
        extendedIngredients = ingredients.map { it.toIngredient() },
        pricePerServing = recipe.pricePerServing
    )
}