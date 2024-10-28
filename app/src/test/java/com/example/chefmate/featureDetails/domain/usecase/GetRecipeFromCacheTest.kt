package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRecipeFromCacheTest {

    private lateinit var repository: FakeDetailsRepository
    private lateinit var getRecipeFromCache: GetRecipeFromCache

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        getRecipeFromCache = GetRecipeFromCache(repository)
    }

    @Test
    fun `Correctly get recipe from cache`() = runBlocking {
        val dummyRecipe = RecipeEntity(
            id = 1,
            title = "Dummy title",
            summary = "Dummy summary",
            instructions = "Dummy instructions",
            servings = 3,
            pricePerServing = 30.0,
            readyInMinutes = 30,
            aggregateLikes = 30,
            imagePath = ""
        )
        val dummyIngredients = listOf(
            IngredientEntity(
                id = 1,
                recipeId = 1,
                name = "Dummy ingredient",
                originalName = "Dummy original name",
                imagePath = "",
                isInShoppingCart = false,
                isBookmarked = true
            ),
            IngredientEntity(
                id = 2,
                recipeId = 1,
                name = "Dummy ingredient 2",
                originalName = "Dummy original name 2",
                imagePath = "",
                isInShoppingCart = false,
                isBookmarked = true
            )
        )
        repository.saveRecipeWithIngredients(dummyRecipe, dummyIngredients)
        val recipe = getRecipeFromCache(1)
        assertEquals(dummyRecipe, recipe.recipe)
        assertEquals(dummyIngredients, recipe.ingredients)
    }
}