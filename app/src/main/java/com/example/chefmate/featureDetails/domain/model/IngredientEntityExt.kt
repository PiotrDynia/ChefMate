package com.example.chefmate.featureDetails.domain.model

import com.example.chefmate.core.data.api.dto.Ingredient

fun IngredientEntity.toIngredient(): Ingredient {
    return Ingredient(
        id = this.id,
        image = this.imagePath,
        name = this.name,
        original = this.originalName
    )
}
