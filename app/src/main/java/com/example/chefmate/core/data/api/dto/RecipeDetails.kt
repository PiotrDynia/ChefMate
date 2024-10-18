package com.example.chefmate.core.data.api.dto

data class RecipeDetails(
    val aggregateLikes: Int,
    val extendedIngredients: List<Ingredient>,
    val id: Int,
    val image: String,
    val instructions: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val title: String,
)