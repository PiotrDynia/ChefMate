package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.core.domain.util.error.ValidationError
import com.example.chefmate.featureSearch.data.repository.FakeSearchRepository
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
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
            SearchFilterSelection(
                query = "Dummy query",
                cuisines = "Italian,French",
                diets = "Vegan,Vegetarian",
                excludedCuisines = "Greek",
                intolerances = "Sesame",
                mealType = "Breakfast,Main course",
                maxCalories = 1000,
                minCalories = 300,
                minServings = 1,
                maxServings = 3,
                sort = "random"
            )
        )

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(2, data.results.size)
        assertEquals("Recipe 1", data.results[0].title)
    }

    @Test
    fun `Should return error for empty query`() = runBlocking {
        val result = searchRecipes(
            SearchFilterSelection(
                query = "",
                cuisines = "Italian,French",
                diets = "Vegan,Vegetarian",
                excludedCuisines = "Greek",
                intolerances = "Sesame",
                mealType = "Breakfast,Main course",
                maxCalories = 1000,
                minCalories = 300,
                minServings = 1,
                maxServings = 3,
                sort = "random"
            )
        )

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(ValidationError.EMPTY, error)
    }

    @Test
    fun `Should return error for too short query`() = runBlocking {
        val result = searchRecipes(
            SearchFilterSelection(
                query = "Aw",
                cuisines = "Italian,French",
                diets = "Vegan,Vegetarian",
                excludedCuisines = "Greek",
                intolerances = "Sesame",
                mealType = "Breakfast,Main course",
                maxCalories = 1000,
                minCalories = 300,
                minServings = 1,
                maxServings = 3,
                sort = "random"
            )
        )

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(ValidationError.TOO_SHORT, error)
    }

    @Test
    fun `Should return error for server error`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 500

        val result = searchRecipes(
            SearchFilterSelection(
                query = "Dummy query",
                cuisines = "Italian,French",
                diets = "Vegan,Vegetarian",
                excludedCuisines = "Greek",
                intolerances = "Sesame",
                mealType = "Breakfast,Main course",
                maxCalories = 1000,
                minCalories = 300,
                minServings = 1,
                maxServings = 3,
                sort = "random"
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
            SearchFilterSelection(
                query = "Dummy query",
                cuisines = "Italian,French",
                diets = "Vegan,Vegetarian",
                excludedCuisines = "Greek",
                intolerances = "Sesame",
                mealType = "Breakfast,Main course",
                maxCalories = 1000,
                minCalories = 300,
                minServings = 1,
                maxServings = 3,
                sort = "random"
            )
        )

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.NO_INTERNET, error)
    }
}