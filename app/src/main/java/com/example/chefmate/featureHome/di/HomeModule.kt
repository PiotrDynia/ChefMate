package com.example.chefmate.featureHome.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.featureHome.domain.usecase.FetchRandomRecipes
import com.example.chefmate.featureHome.domain.usecase.FetchRecipes
import com.example.chefmate.featureHome.domain.usecase.GetAutocompleteRecipes
import com.example.chefmate.featureHome.domain.usecase.HomeUseCases
import com.example.chefmate.featureHome.domain.usecase.ReadDietPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeUseCases(dataStoreRepository: DataStoreRepository, apiService: APIService) : HomeUseCases {
        return HomeUseCases(
            readDietPreferences = ReadDietPreferences(dataStoreRepository),
            fetchRandomRecipes = FetchRandomRecipes(apiService),
            fetchRecipes = FetchRecipes(apiService),
            getAutocompleteRecipes = GetAutocompleteRecipes(apiService)
        )
    }
}