package com.example.chefmate.featureSplash.di

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.featureSplash.domain.usecase.ReadOnBoardingState
import com.example.chefmate.featureSplash.domain.usecase.SplashUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashModule {

    @Provides
    @Singleton
    fun provideSplashUseCases(dataStoreRepository: DataStoreRepository) : SplashUseCases {
        return SplashUseCases(readOnBoardingState = ReadOnBoardingState(dataStoreRepository))
    }
}