package com.example.chefmate.featureDetails.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.db.ChefMateDatabase
import com.example.chefmate.core.domain.util.imageManager.ImageCacheManager
import com.example.chefmate.featureDetails.data.repository.DetailsRepositoryImpl
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository
import com.example.chefmate.featureDetails.domain.usecase.CheckIngredientIsInShoppingList
import com.example.chefmate.featureShoppingList.domain.usecase.AddShoppingListItem
import com.example.chefmate.featureDetails.domain.usecase.CheckRecipeIsBookmarked
import com.example.chefmate.featureDetails.domain.usecase.DeleteRecipeFromCache
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import com.example.chefmate.featureDetails.domain.usecase.GetRecipeDetailsFromAPI
import com.example.chefmate.featureDetails.domain.usecase.GetRecipeFromCache
import com.example.chefmate.featureDetails.domain.usecase.SaveRecipe
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import com.example.chefmate.featureShoppingList.domain.usecase.CheckIngredientIsBookmarked
import com.example.chefmate.featureShoppingList.domain.usecase.RemoveShoppingListItem
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
            recipeDetailsDao = db.detailsDao()
        )
    }

    @Provides
    fun provideDetailsUseCases(
        detailsRepository: DetailsRepository,
        shoppingListRepository: ShoppingListRepository,
        imageManager: ImageCacheManager
    ) : DetailsUseCases {
        return DetailsUseCases(
            getRecipeDetailsFromAPI = GetRecipeDetailsFromAPI(detailsRepository),
            deleteRecipeFromCache = DeleteRecipeFromCache(repository = detailsRepository, imageManager = imageManager),
            checkRecipeIsBookmarked = CheckRecipeIsBookmarked(detailsRepository),
            checkIngredientIsBookmarked = CheckIngredientIsBookmarked(shoppingListRepository),
            addShoppingListItem = AddShoppingListItem(shoppingListRepository),
            deleteShoppingListItem = RemoveShoppingListItem(shoppingListRepository),
            getRecipeFromCache = GetRecipeFromCache(detailsRepository),
            checkIngredientIsInShoppingList = CheckIngredientIsInShoppingList(detailsRepository),
            saveRecipe = SaveRecipe(repository = detailsRepository, imageManager = imageManager)
        )
    }
}