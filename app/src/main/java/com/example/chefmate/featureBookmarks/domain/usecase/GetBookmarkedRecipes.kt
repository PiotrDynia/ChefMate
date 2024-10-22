package com.example.chefmate.featureBookmarks.domain.usecase

import com.example.chefmate.featureBookmarks.domain.repository.BookmarksRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class GetBookmarkedRecipes(
    private val repository: BookmarksRepository
) {

    operator fun invoke() : Flow<List<RecipeEntity>> {
        return repository.getBookmarkedRecipes()
    }
}