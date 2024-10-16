package com.example.chefmate.core.data.api.dto

data class RecipeDetails(
    val aggregateLikes: Int,
    val cuisines: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val id: Int,
    val image: String,
    val instructions: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val title: String,
)