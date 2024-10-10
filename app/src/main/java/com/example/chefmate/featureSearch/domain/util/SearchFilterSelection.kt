package com.example.chefmate.featureSearch.domain.util

data class SearchFilterSelection(
    val query: String,
    val cuisines: Set<String>,
    val diets: Set<String>,
    val intolerances: Set<String>,
    val mealType: Set<String>,
    val excludedCuisines: Set<String>,
    val minServings: Int,
    val maxServings: Int,
    val minCalories: Int,
    val maxCalories: Int,
    val sort: String
)