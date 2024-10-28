package com.example.chefmate.featureShoppingList.domain.usecase

import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.data.repository.FakeShoppingListRepository
import com.example.chefmate.featureShoppingList.domain.repository.ShoppingListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CheckIngredientIsBookmarkedTest {

    private lateinit var repository: ShoppingListRepository
    private lateinit var checkIngredientIsBookmarked: CheckIngredientIsBookmarked

    @Before
    fun setup() {
        repository = FakeShoppingListRepository()
        checkIngredientIsBookmarked = CheckIngredientIsBookmarked(repository)
    }

    @Test
    fun `checkIngredientIsBookmarked returns true when ingredient is bookmarked`() = runBlocking {
        val ingredient = IngredientEntity(
            id = 1,
            recipeId = 0,
            name = "",
            originalName = "",
            imagePath = "",
            isBookmarked = true,
            isInShoppingCart = true
        )
        repository.addShoppingListItem(ingredient)

        val result = checkIngredientIsBookmarked(ingredient.id)

        assertTrue(result)
    }

    @Test
    fun `checkIngredientIsBookmarked returns false when ingredient is not bookmarked`() =
        runBlocking {
            val ingredient = IngredientEntity(
                id = 1,
                recipeId = 0,
                name = "",
                originalName = "",
                imagePath = "",
                isBookmarked = false,
                isInShoppingCart = true
            )
            repository.addShoppingListItem(ingredient)

            val result = checkIngredientIsBookmarked(ingredient.id)
            assertFalse(result)
        }
}