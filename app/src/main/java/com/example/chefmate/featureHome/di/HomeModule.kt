package com.example.chefmate.featureHome.di

import com.example.chefmate.core.domain.repository.DataStoreRepository
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
    fun provideHomeUseCases(dataStoreRepository: DataStoreRepository) : HomeUseCases {
        return HomeUseCases(readDietPreferences = ReadDietPreferences(dataStoreRepository))
    }
}