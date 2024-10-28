package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.toIngredient
import com.example.chefmate.featureShoppingList.data.repository.FakeShoppingListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddShoppingListItemTest {

    private lateinit var repository: FakeShoppingListRepository
    private lateinit var addShoppingListItem: AddShoppingListItem

    @Before
    fun setup() {
        repository = FakeShoppingListRepository()
        addShoppingListItem = AddShoppingListItem(repository)
    }

    @Test
    fun `addShoppingListItem adds ingredient to list`() = runBlocking {
        val ingredient = IngredientEntity(
            id = 1,
            recipeId = 0,
            name = "",
            originalName = "",
            imagePath = "",
            isBookmarked = false,
            isInShoppingCart = true
        )
        addShoppingListItem(ingredient.toIngredient(), recipeId = ingredient.recipeId)

        val result = repository.shoppingList.contains(ingredient)
        assertTrue(result)
    }
}