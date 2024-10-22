package com.example.chefmate.featureBookmarks.data.repository

import com.example.chefmate.featureBookmarks.data.dataSource.BookmarksDao
import com.example.chefmate.featureBookmarks.domain.repository.BookmarksRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureDetails.domain.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

class BookmarksRepositoryImpl(
    private val bookmarksDao: BookmarksDao
) : BookmarksRepository {

    override fun getBookmarkedRecipes(): Flow<List<RecipeEntity>> {
        return bookmarksDao.getRecipes()
    }
}