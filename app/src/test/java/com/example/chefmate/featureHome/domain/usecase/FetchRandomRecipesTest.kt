package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.featureHome.data.repository.FakeHomeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FetchRandomRecipesTest {

    private lateinit var repository: FakeHomeRepository
    private lateinit var fetchRandomRecipes: FetchRandomRecipes

    @Before
    fun setUp() {
        repository = FakeHomeRepository()
        fetchRandomRecipes = FetchRandomRecipes(repository)
    }

    @Test
    fun `Should return random recipes successfully`() = runBlocking {
        val fakeRecipes = GetRandomRecipeResult(
            listOf(
                RecipeSimple(id = 1, title = "Recipe 1", image = "Dummy image", summary = "Dummy summary"),
                RecipeSimple(id = 2, title = "Recipe 2", image = "Dummy image", summary = "Dummy summary")
            )
        )
        repository.randomRecipes = fakeRecipes

        val result = fetchRandomRecipes()

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(2, data.recipes.size)
        assertEquals("Recipe 1", data.recipes[0].title)
    }

    @Test
    fun `Should return error for server error`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 500

        val result = fetchRandomRecipes()

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.SERVER_ERROR, error)
    }

    @Test
    fun `Should return error for no internet`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 0

        val result = fetchRandomRecipes()

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.NO_INTERNET, error)
    }

    @Test
    fun `Should return error for too many requests`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 429

        val result = fetchRandomRecipes()

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.TOO_MANY_REQUESTS, error)
    }
}