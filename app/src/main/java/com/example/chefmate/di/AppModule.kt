package com.example.chefmate.di

import android.content.Context
import com.example.chefmate.core.data.repository.DataStoreRepository
import com.example.chefmate.featureOnboarding.domain.usecase.SaveDietPreferences
import com.example.chefmate.featureOnboarding.domain.usecase.SaveOnBoardingState
import com.example.chefmate.featureOnboarding.domain.usecase.WelcomeUseCases
import com.example.chefmate.featureSplash.domain.usecase.ReadOnBoardingState
import com.example.chefmate.featureSplash.domain.usecase.SplashUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideWelcomeUseCases(dataStoreRepository: DataStoreRepository) : WelcomeUseCases {
        return WelcomeUseCases(
            saveOnBoardingState = SaveOnBoardingState(dataStoreRepository),
            saveDietPreferences = SaveDietPreferences(dataStoreRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSplashUseCases(dataStoreRepository: DataStoreRepository) : SplashUseCases {
        return SplashUseCases(readOnBoardingState = ReadOnBoardingState(dataStoreRepository))
    }
}