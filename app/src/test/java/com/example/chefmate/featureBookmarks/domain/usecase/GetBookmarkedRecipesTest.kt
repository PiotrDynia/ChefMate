package com.example.chefmate.featureBookmarks.domain.usecase

import com.example.chefmate.featureBookmarks.data.repository.FakeBookmarksRepository
import com.example.chefmate.featureDetails.domain.model.RecipeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetBookmarkedRecipesTest {

    private lateinit var repository: FakeBookmarksRepository
    private lateinit var getBookmarkedRecipes: GetBookmarkedRecipes

    @Before
    fun setUp() {
        repository = FakeBookmarksRepository()
        getBookmarkedRecipes = GetBookmarkedRecipes(repository)
    }

    @Test
    fun `getBookmarkedRecipes returns flow of bookmarked recipes`() = runBlocking {
        repository.bookmarkedRecipes = mutableListOf(
            RecipeEntity(
                id = 1,
                title = "Recipe 1",
                summary = "Summary 1",
                instructions = "Instructions 1",
                servings = 2,
                readyInMinutes = 30,
                pricePerServing = 30.0,
                aggregateLikes = 5,
                imagePath = ""
            ),
            RecipeEntity(
                id = 2,
                title = "Recipe 2",
                summary = "Summary 2",
                instructions = "Instructions 2",
                servings = 1,
                readyInMinutes = 19,
                pricePerServing = 30.0,
                aggregateLikes = 3,
                imagePath = ""
            )
        )

        val flow = getBookmarkedRecipes()

        assertNotNull(flow)
        assert(flow.first().isNotEmpty())
        assert(flow.first().size == 2)
        assert(flow.first() == repository.bookmarkedRecipes)
    }
}