package com.example.chefmate.featureShoppingList.presentation

import com.example.chefmate.featureDetails.domain.model.IngredientEntity

sealed class ShoppingListEvent {
    data object OnUndoClick : ShoppingListEvent()
    data class OnRemoveFromShoppingList(val ingredient: IngredientEntity) : ShoppingListEvent()
}
