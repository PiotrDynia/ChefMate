package com.example.chefmate.featureDetails.domain.usecase

data class DetailsUseCases(
    val getRecipeDetailsFromAPI: GetRecipeDetailsFromAPI,
    val checkRecipeIsBookmarked: CheckRecipeIsBookmarked,
    val deleteRecipeFromCache: DeleteRecipeFromCache,
    val getRecipeFromCache: GetRecipeFromCache,
    val saveRecipe: SaveRecipe
)