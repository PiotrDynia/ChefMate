package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.featureSearch.data.repository.FakeSearchRepository
import com.example.chefmate.featureSearch.presentation.SearchState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchRecipesTest {
    private lateinit var repository: FakeSearchRepository
    private lateinit var searchRecipes: SearchRecipes

    @Before
    fun setUp() {
        repository = FakeSearchRepository()
        searchRecipes = SearchRecipes(repository)
    }

    @Test
    fun `Should search for recipes successfully`() = runBlocking {
        val fakeRecipes = GetRecipeResult(
            results = listOf(
                RecipeSimple(id = 1, title = "Recipe 1", image = "Dummy image", summary = "Dummy summary"),
                RecipeSimple(id = 2, title = "Recipe 2", image = "Dummy image", summary = "Dummy summary")
            )
        )
        repository.recipes = fakeRecipes
        val result = searchRecipes(
            SearchState(
                searchInput = "Dummy query",
                selectedCuisines = setOf("Italian", "French"),
                selectedDiets = setOf("Vegan", "Vegetarian"),
                excludedCuisines = setOf("Greek"),
                selectedIntolerances = setOf("Sesame"),
                selectedMealTypes = setOf("Breakfast,Main course"),
                caloriesSliderPosition = 100.0f..200.0f,
                servingsSliderPosition = 2.0f..3.0f,
                selectedSortType = "random"
            )
        )

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(2, data.results.size)
        assertEquals("Recipe 1", data.results[0].title)
    }

    @Test
    fun `Should return error for server error`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 500
        val result = searchRecipes(
            SearchState(
                searchInput = "Dummy query",
                selectedCuisines = setOf("Italian", "French"),
                selectedDiets = setOf("Vegan", "Vegetarian"),
                excludedCuisines = setOf("Greek"),
                selectedIntolerances = setOf("Sesame"),
                selectedMealTypes = setOf("Breakfast,Main course"),
                caloriesSliderPosition = 100.0f..200.0f,
                servingsSliderPosition = 2.0f..3.0f,
                selectedSortType = "random"
            )
        )
        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.SERVER_ERROR, error)
    }

    @Test
    fun `Should return error for no internet`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 0
        val result = searchRecipes(
            SearchState(
                searchInput = "Dummy query",
                selectedCuisines = setOf("Italian", "French"),
                selectedDiets = setOf("Vegan", "Vegetarian"),
                excludedCuisines = setOf("Greek"),
                selectedIntolerances = setOf("Sesame"),
                selectedMealTypes = setOf("Breakfast,Main course"),
                caloriesSliderPosition = 100.0f..200.0f,
                servingsSliderPosition = 2.0f..3.0f,
                selectedSortType = "random"
            )
        )
        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.NO_INTERNET, error)
    }
}