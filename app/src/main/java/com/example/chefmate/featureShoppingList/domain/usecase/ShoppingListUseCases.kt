package com.example.chefmate.featureShoppingList.domain.usecase

data class ShoppingListUseCases(
    val checkIngredientIsBookmarked: CheckIngredientIsBookmarked,
    val addShoppingListItem: AddShoppingListItem,
    val removeShoppingListItem: RemoveShoppingListItem,
    val getShoppingList: GetShoppingList
)
