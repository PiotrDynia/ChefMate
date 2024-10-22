package com.example.chefmate.featureBookmarks.domain.repository

import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {
    fun getBookmarkedRecipes(): Flow<List<RecipeEntity>>
}