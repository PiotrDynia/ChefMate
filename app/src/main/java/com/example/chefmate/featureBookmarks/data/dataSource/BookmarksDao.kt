package com.example.chefmate.featureBookmarks.data.dataSource

import androidx.room.Dao
import androidx.room.Query
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarksDao {

    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<RecipeEntity>>
}