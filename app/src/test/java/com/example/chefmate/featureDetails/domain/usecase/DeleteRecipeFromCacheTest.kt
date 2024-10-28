package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.domain.util.FakeImageManager
import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeleteRecipeFromCacheTest {

    private lateinit var repository: FakeDetailsRepository
    private lateinit var deleteRecipeFromCache: DeleteRecipeFromCache
    private lateinit var imageManager: FakeImageManager

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        imageManager = FakeImageManager()
        deleteRecipeFromCache = DeleteRecipeFromCache(repository, imageManager)
    }

    @Test
    fun `Correctly delete recipe from cache`() = runBlocking {
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
            ingredients = listOf(
                IngredientEntity(
                    id = 1,
                    recipeId = 1,
                    name = "Dummy ingredient",
                    originalName = "Dummy original name",
                    imagePath = "",
                    isBookmarked = true,
                    isInShoppingCart = false
                )
            )
        )
        imageManager.cacheImage("dummy url")
        assert(repository.cachedRecipes.size == 1)
        assertTrue(imageManager.isImageCached("dummy url"))

        deleteRecipeFromCache(1, "dummy url")

        assert(repository.cachedRecipes.isEmpty())
        assertFalse(imageManager.isImageCached("dummy url"))
    }
}