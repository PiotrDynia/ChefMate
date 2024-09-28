package com.example.chefmate.core.data.api

import com.example.chefmate.core.domain.util.RecipeSimple

data class GetRandomRecipeResult(
    val recipes: List<RecipeSimple>
)