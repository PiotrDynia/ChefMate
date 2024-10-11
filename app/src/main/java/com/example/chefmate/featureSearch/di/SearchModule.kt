package com.example.chefmate.featureSearch.di

import com.example.chefmate.featureSearch.domain.usecase.SearchRecipes
import com.example.chefmate.featureSearch.domain.usecase.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    fun provideSearchUseCases() : SearchUseCases {
        return SearchUseCases(searchRecipes = SearchRecipes())
    }
}