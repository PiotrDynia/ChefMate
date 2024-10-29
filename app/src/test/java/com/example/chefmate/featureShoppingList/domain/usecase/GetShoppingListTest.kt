package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.data.repository.FakeShoppingListRepository
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetShoppingListTest {

    private lateinit var repository: ShoppingListRepository
    private lateinit var getShoppingList: GetShoppingList

    @Before
    fun setup() {
        repository = FakeShoppingListRepository()
        getShoppingList = GetShoppingList(repository)
    }

    @Test
    fun `getShoppingList returns correct list`() = runBlocking {
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

        val result = getShoppingList().first()

        assertEquals(listOf(ingredient), result)
    }
}