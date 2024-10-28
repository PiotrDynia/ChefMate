package com.example.chefmate.featureShoppingList.domain.usecase

data class ShoppingListUseCases(
    val addShoppingListItem: AddShoppingListItem,
    val checkIngredientIsBookmarked: CheckIngredientIsBookmarked,
    val removeShoppingListItem: RemoveShoppingListItem,
    val getShoppingList: GetShoppingList
)
