package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.data.repository.FakeShoppingListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class RemoveShoppingListItemTest {

    private lateinit var repository: FakeShoppingListRepository
    private lateinit var removeShoppingListItem: RemoveShoppingListItem

    @Before
    fun setup() {
        repository = FakeShoppingListRepository()
        removeShoppingListItem = RemoveShoppingListItem(repository)
    }

    @Test
    fun `removeShoppingListItem removes ingredient from list`() = runBlocking {
        val ingredient = IngredientEntity(
            id = 1,
            recipeId = 0,
            name = "",
            originalName = "",
            imagePath = "",
            isBookmarked = false,
            isInShoppingList = true
        )
        repository.addShoppingListItem(ingredient)

        removeShoppingListItem(ingredient.id)

        val result = repository.shoppingList.contains(ingredient)
        assertFalse(result)
    }
}