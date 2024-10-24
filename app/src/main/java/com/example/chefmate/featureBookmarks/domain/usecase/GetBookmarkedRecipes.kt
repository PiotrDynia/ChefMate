package com.example.chefmate.featureBookmarks.domain.usecase

import com.example.chefmate.featureBookmarks.domain.repository.BookmarksRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

class GetBookmarkedRecipes(
    private val repository: BookmarksRepository
) {

    operator fun invoke() : Flow<List<RecipeEntity>> {
        return repository.getBookmarkedRecipes()
    }
}