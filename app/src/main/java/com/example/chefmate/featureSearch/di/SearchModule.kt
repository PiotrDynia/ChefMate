package com.example.chefmate.featureSearch.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.usecase.ReadDietPreferences
import com.example.chefmate.featureSearch.data.repository.SearchRepositoryImpl
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
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
    fun provideSearchRepository(apiService: APIService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

    @Provides
    fun provideSearchUseCases(searchRepository: SearchRepository, dataStoreRepository: DataStoreRepository): SearchUseCases {
        return SearchUseCases(
            searchRecipes = SearchRecipes(searchRepository),
            readDietPreferences = ReadDietPreferences(dataStoreRepository)
        )
    }
}