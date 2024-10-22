package com.example.chefmate.featureBookmarks.presentation

import com.example.chefmate.featureDetails.domain.model.RecipeEntity

data class BookmarksState(
    val bookmarkedRecipes: List<RecipeEntity> = emptyList(),
    val searchInput: String = "",
    val isLoading: Boolean = true
)
