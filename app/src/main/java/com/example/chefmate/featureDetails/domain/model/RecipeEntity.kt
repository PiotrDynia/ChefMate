package com.example.chefmate.featureDetails.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "recipe")
class RecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val summary: String,
    val instructions: String,
    val servings: Int,
    val readyInMinutes: Int,
    val aggregateLikes: Int,
    val imagePath: String
)

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

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>
)