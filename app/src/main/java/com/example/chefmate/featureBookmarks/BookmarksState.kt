package com.example.chefmate.featureBookmarks

import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients

data class BookmarksState(
    val bookmarkedRecipes: List<RecipeWithIngredients> = emptyList()
)
