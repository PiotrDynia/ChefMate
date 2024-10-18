package com.example.chefmate.featureDetails.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.db.ChefMateDatabase
import com.example.chefmate.core.domain.util.ImageCacheManager
import com.example.chefmate.featureDetails.data.repository.DetailsRepositoryImpl
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository
import com.example.chefmate.featureDetails.domain.usecase.CheckRecipeIsBookmarked
import com.example.chefmate.featureDetails.domain.usecase.DeleteRecipeFromCache
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import com.example.chefmate.featureDetails.domain.usecase.GetRecipeDetailsFromAPI
import com.example.chefmate.featureDetails.domain.usecase.GetRecipeFromCache
import com.example.chefmate.featureDetails.domain.usecase.SaveRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    fun provideDetailsRepository(apiService: APIService, db: ChefMateDatabase): DetailsRepository {
        return DetailsRepositoryImpl(
            apiService = apiService,
            recipeDao = db.detailsDao()
        )
    }

    @Provides
    fun provideDetailsUseCases(repository: DetailsRepository, imageManager: ImageCacheManager) : DetailsUseCases {
        return DetailsUseCases(
            getRecipeDetailsFromAPI = GetRecipeDetailsFromAPI(repository),
            deleteRecipeFromCache = DeleteRecipeFromCache(repository = repository, imageManager = imageManager),
            checkRecipeIsBookmarked = CheckRecipeIsBookmarked(repository),
            getRecipeFromCache = GetRecipeFromCache(repository),
            saveRecipe = SaveRecipe(repository = repository, imageManager = imageManager)
        )
    }
}