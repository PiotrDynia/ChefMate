package com.example.chefmate.featureHome.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.featureHome.data.repository.HomeRepositoryImpl
import com.example.chefmate.featureHome.domain.repository.HomeRepository
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
    fun provideHomeRepository(apiService: APIService): HomeRepository {
        return HomeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(dataStoreRepository: DataStoreRepository, homeRepository: HomeRepository) : HomeUseCases {
        return HomeUseCases(
            readDietPreferences = ReadDietPreferences(dataStoreRepository),
            fetchRandomRecipes = FetchRandomRecipes(homeRepository),
            fetchRecipes = FetchRecipes(homeRepository),
            getAutocompleteRecipes = GetAutocompleteRecipes(homeRepository)
        )
    }
}