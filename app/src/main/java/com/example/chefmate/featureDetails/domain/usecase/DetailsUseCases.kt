package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureShoppingList.domain.usecase.AddShoppingListItem
import com.example.chefmate.featureShoppingList.domain.usecase.CheckIngredientIsBookmarked
import com.example.chefmate.featureShoppingList.domain.usecase.RemoveShoppingListItem

data class DetailsUseCases(
    val getRecipeDetailsFromAPI: GetRecipeDetailsFromAPI,
    val checkRecipeIsBookmarked: CheckRecipeIsBookmarked,
    val deleteRecipeFromCache: DeleteRecipeFromCache,
    val getRecipeFromCache: GetRecipeFromCache,
    val addShoppingListItem: AddShoppingListItem,
    val checkIngredientIsBookmarked: CheckIngredientIsBookmarked,
    val checkIngredientIsInShoppingCart: CheckIngredientIsInShoppingCart,
    val deleteShoppingListItem: RemoveShoppingListItem,
    val saveRecipe: SaveRecipe
)