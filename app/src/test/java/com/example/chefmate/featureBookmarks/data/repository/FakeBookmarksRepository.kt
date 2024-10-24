package com.example.chefmate.featureBookmarks.data.repository

import com.example.chefmate.featureBookmarks.domain.repository.BookmarksRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookmarksRepository : BookmarksRepository {

    var bookmarkedRecipes = mutableListOf<RecipeEntity>()

    override fun getBookmarkedRecipes(): Flow<List<RecipeEntity>> {
        return flow { emit(bookmarkedRecipes) }
    }

}