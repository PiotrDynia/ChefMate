package com.example.chefmate.featureDetails.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.featureDetails.data.repository.DetailsRepositoryImpl
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import com.example.chefmate.featureDetails.domain.usecase.GetRecipeDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResultsModule {

    @Provides
    fun provideDetailsRepository(apiService: APIService): DetailsRepository {
        return DetailsRepositoryImpl(apiService)
    }

    @Provides
    fun provideDetailsUseCases(repository: DetailsRepository) : DetailsUseCases {
        return DetailsUseCases(
            getRecipeDetails = GetRecipeDetails(repository)
        )
    }
}