package com.example.chefmate.featureDetails.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredient",
    indices = [Index(value = ["recipeId"])]
)
data class IngredientEntity(
    @PrimaryKey val id: Int,
    val recipeId: Int,
    val name: String,
    val originalName: String,
    val imagePath: String,
    val isInShoppingList: Boolean,
    val isBookmarked: Boolean
)
