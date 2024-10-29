package com.example.chefmate.featureShoppingList.di

import com.example.chefmate.core.data.db.ChefMateDatabase
import com.example.chefmate.featureShoppingList.data.repository.ShoppingListRepositoryImpl
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import com.example.chefmate.featureShoppingList.domain.usecase.AddShoppingListItem
import com.example.chefmate.featureShoppingList.domain.usecase.CheckIngredientIsBookmarked
import com.example.chefmate.featureShoppingList.domain.usecase.GetShoppingList
import com.example.chefmate.featureShoppingList.domain.usecase.RemoveShoppingListItem
import com.example.chefmate.featureShoppingList.domain.usecase.ShoppingListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ShoppingListModule {

    @Provides
    fun provideShoppingListRepository(db: ChefMateDatabase): ShoppingListRepository {
        return ShoppingListRepositoryImpl(
            dao = db.shoppingListDao()
        )
    }

    @Provides
    fun provideShoppingListUseCases(repository: ShoppingListRepository) : ShoppingListUseCases {
        return ShoppingListUseCases(
            addShoppingListItem = AddShoppingListItem(repository),
            checkIngredientIsBookmarked = CheckIngredientIsBookmarked(repository),
            getShoppingList = GetShoppingList(repository),
            removeShoppingListItem = RemoveShoppingListItem(repository)
        )
    }
}