package com.example.chefmate.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chefmate.featureBookmarks.data.dataSource.BookmarksDao
import com.example.chefmate.featureDetails.data.dataSource.RecipeDetailsDao
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import com.example.chefmate.featureShoppingList.data.dataSource.ShoppingListDao

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class],
    version = 3
)
abstract class ChefMateDatabase : RoomDatabase() {
    abstract fun detailsDao() : RecipeDetailsDao
    abstract fun bookmarksDao() : BookmarksDao
    abstract fun shoppingListDao() : ShoppingListDao
}