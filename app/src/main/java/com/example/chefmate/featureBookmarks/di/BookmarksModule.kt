package com.example.chefmate.featureBookmarks.di

import com.example.chefmate.core.data.db.ChefMateDatabase
import com.example.chefmate.featureBookmarks.data.repository.BookmarksRepositoryImpl
import com.example.chefmate.featureBookmarks.domain.repository.BookmarksRepository
import com.example.chefmate.featureBookmarks.domain.usecase.BookmarksUseCases
import com.example.chefmate.featureBookmarks.domain.usecase.GetBookmarkedRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BookmarksModule {

    @Provides
    fun provideBookmarksRepository(db: ChefMateDatabase): BookmarksRepository {
        return BookmarksRepositoryImpl(db.bookmarksDao())
    }

    @Provides
    fun provideBookmarksUseCases(repository: BookmarksRepository): BookmarksUseCases {
        return BookmarksUseCases(
            getBookmarkedRecipes = GetBookmarkedRecipes(repository)
        )
    }
}