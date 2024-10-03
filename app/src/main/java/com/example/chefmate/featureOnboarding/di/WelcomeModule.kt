package com.example.chefmate.featureOnboarding.di

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.usecase.SaveDietPreferences
import com.example.chefmate.featureOnboarding.domain.usecase.SaveOnBoardingState
import com.example.chefmate.featureOnboarding.domain.usecase.WelcomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WelcomeModule {

    @Provides
    @Singleton
    fun provideWelcomeUseCases(dataStoreRepository: DataStoreRepository) : WelcomeUseCases {
        return WelcomeUseCases(
            saveOnBoardingState = SaveOnBoardingState(dataStoreRepository),
            saveDietPreferences = SaveDietPreferences(dataStoreRepository)
        )
    }
}