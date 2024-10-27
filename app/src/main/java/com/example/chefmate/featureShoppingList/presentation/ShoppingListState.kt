package com.example.chefmate.featureShoppingList.presentation

import com.example.chefmate.featureDetails.domain.model.IngredientEntity

data class ShoppingListState(
    val shoppingList: List<IngredientEntity> = emptyList(),
    val isLoading: Boolean = true
)