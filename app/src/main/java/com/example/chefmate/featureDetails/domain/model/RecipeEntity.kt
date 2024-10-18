package com.example.chefmate.featureDetails.domain.model

import androidx.room.Embedded
import androidx.room.Entity
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

data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>
)