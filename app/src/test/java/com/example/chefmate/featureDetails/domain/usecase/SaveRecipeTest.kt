package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.data.api.dto.Ingredient
import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.core.domain.util.FakeImageManager
import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SaveRecipeTest {
    private lateinit var repository: FakeDetailsRepository
    private lateinit var imageManager: FakeImageManager
    private lateinit var saveRecipe: SaveRecipe

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        imageManager = FakeImageManager()
        saveRecipe = SaveRecipe(repository, imageManager)
    }

    @Test
    fun `Correctly save recipe`() = runBlocking {
        val dummyRecipe = RecipeDetails(
            id = 1,
            title = "Dummy title",
            summary = "Dummy summary",
            instructions = "Dummy instructions",
            servings = 3,
            aggregateLikes = 30,
            readyInMinutes = 30,
            pricePerServing = 30.0,
            image = "",
            extendedIngredients = listOf(
                Ingredient(
                    id = 1,
                    name = "Dummy ingredient",
                    original = "Dummy original name",
                    image = ""
                ),
                Ingredient(
                    id = 2,
                    name = "Dummy ingredient 2",
                    original = "Dummy original name 2",
                    image = ""
                )
            )
        )
        saveRecipe(dummyRecipe)
        val cachedRecipe = repository.cachedRecipes.first()
        assertEquals(dummyRecipe.id, cachedRecipe.recipe.id)
        assertEquals(dummyRecipe.title, cachedRecipe.recipe.title)
    }
}