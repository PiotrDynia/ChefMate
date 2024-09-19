package com.example.chefmate.di

import android.content.Context
import com.example.chefmate.core.data.repository.DataStoreRepositoryImpl
import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.featureHome.domain.usecase.HomeUseCases
import com.example.chefmate.featureHome.domain.usecase.ReadDietPreferences
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
object CoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) : DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }
}