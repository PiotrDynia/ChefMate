package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.core.data.api.dto.Ingredient
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository

class AddShoppingListItem(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(item: Ingredient, recipeId: Int) {
        val isIngredientBookmarked = repository.isIngredientBookmarked(item.id)

        if (isIngredientBookmarked) {
            repository.addBookmarkedIngredientToShoppingList(item.id)
        } else {
            val ingredientEntity = IngredientEntity(
                id = item.id,
                recipeId = recipeId,
                name = item.name,
                originalName = item.original,
                imagePath = "",
                isInShoppingList = true,
                isBookmarked = false
            )
            repository.addShoppingListItem(ingredientEntity)
        }
    }
}