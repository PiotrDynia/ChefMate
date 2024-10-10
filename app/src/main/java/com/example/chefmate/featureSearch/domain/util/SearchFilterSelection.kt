package com.example.chefmate.featureSearch.domain.util

data class SearchFilterSelection(
    val query: String,
    val cuisines: String,
    val diets: String,
    val intolerances: String,
    val mealType: String,
    val excludedCuisines: String,
    val minServings: Int,
    val maxServings: Int,
    val minCalories: Int,
    val maxCalories: Int,
    val sort: String
)