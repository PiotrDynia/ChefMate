package com.example.chefmate.featureDetails.presentation

import com.example.chefmate.core.data.api.dto.Ingredient

sealed class DetailsEvent {
    data object OnBackClick : DetailsEvent()
    data object OnBookmarkClick : DetailsEvent()
    data class OnAddIngredientToShoppingListClick(val ingredient: Ingredient) : DetailsEvent()
    data class OnDeleteIngredientFromShoppingList(val ingredient: Ingredient) : DetailsEvent()
    data object OnUndoAddIngredientToShoppingList : DetailsEvent()
}