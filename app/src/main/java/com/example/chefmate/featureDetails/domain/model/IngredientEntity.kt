package com.example.chefmate.featureDetails.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredient",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["recipeId"])]
)
data class IngredientEntity(
    @PrimaryKey val id: Int,
    val recipeId: Int,
    val name: String,
    val original: String,
    val imagePath: String,
    val isShoppingItem: Boolean
)
