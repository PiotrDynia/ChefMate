package com.example.chefmate.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chefmate.featureDetails.data.dataSource.RecipeDao
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class],
    version = 1
)
abstract class ChefMateDatabase : RoomDatabase() {
    abstract fun detailsDao() : RecipeDao
}