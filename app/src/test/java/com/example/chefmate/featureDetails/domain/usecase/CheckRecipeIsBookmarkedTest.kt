package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CheckRecipeIsBookmarkedTest {

    private lateinit var repository: FakeDetailsRepository
    private lateinit var checkRecipeIsBookmarked: CheckRecipeIsBookmarked

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        checkRecipeIsBookmarked = CheckRecipeIsBookmarked(repository)
    }

    @Test
    fun `Return true for existing recipe`() = runBlocking {
        repository.saveRecipeWithIngredients(
            recipe = RecipeEntity(
                id = 1,
                title = "Dummy title",
                summary = "Dummy summary",
                instructions = "Dummy instructions",
                servings = 3,
                readyInMinutes = 30,
                aggregateLikes = 5,
                imagePath = "",
                pricePerServing = 30.0
            ),
            ingredients = emptyList()
        )
        assertTrue(checkRecipeIsBookmarked(1))
    }

    @Test
    fun `Return false for non existing recipe`() = runBlocking {
        repository.saveRecipeWithIngredients(
            recipe = RecipeEntity(
                id = 1,
                title = "Dummy title",
                summary = "Dummy summary",
                instructions = "Dummy instructions",
                servings = 3,
                readyInMinutes = 30,
                aggregateLikes = 5,
                imagePath = "",
                pricePerServing = 30.0
            ),
            ingredients = emptyList()
        )
        assertFalse(checkRecipeIsBookmarked(2))
    }
}