package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.core.domain.util.userPreferences.MealType
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.featureHome.data.repository.FakeHomeRepository
import com.example.chefmate.featureHome.domain.util.PreferencesSelection
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class FetchRecipesTest {

    private lateinit var repository: FakeHomeRepository
    private lateinit var fetchRecipes: FetchRecipes

    @Before
    fun setUp() {
        repository = FakeHomeRepository()
        fetchRecipes = FetchRecipes(repository)
    }

    @Test
    fun `Should return recipes successfully`() = runBlocking {
        val fakeRecipes = GetRecipeResult(
            results = listOf(
                RecipeSimple(id = 1, title = "Recipe 1", image = "Dummy image", summary = "Dummy summary"),
                RecipeSimple(id = 2, title = "Recipe 2", image = "Dummy image", summary = "Dummy summary")
            )
        )
        repository.recipes = fakeRecipes

        val result = fetchRecipes(
            PreferencesSelection(
                selectedCuisines = setOf(Cuisine.ITALIAN, Cuisine.ASIAN),
                selectedDiets = setOf(Diet.VEGAN),
                selectedIntolerances = setOf(Intolerance.GLUTEN),
                selectedMealTypes = setOf(MealType.SOUP, MealType.BREAD)
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

        val result = fetchRecipes(
            PreferencesSelection(
                selectedCuisines = setOf(Cuisine.ITALIAN, Cuisine.ASIAN),
                selectedDiets = setOf(Diet.VEGAN),
                selectedIntolerances = setOf(Intolerance.GLUTEN),
                selectedMealTypes = setOf(MealType.SOUP, MealType.BREAD)
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

        val result = fetchRecipes(
            PreferencesSelection(
                selectedCuisines = setOf(Cuisine.ITALIAN, Cuisine.ASIAN),
                selectedDiets = setOf(Diet.VEGAN),
                selectedIntolerances = setOf(Intolerance.GLUTEN),
                selectedMealTypes = setOf(MealType.SOUP, MealType.BREAD)
            )
        )

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.NO_INTERNET, error)
    }

    @Test
    fun `Should return error for too many requests`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 429

        val result = fetchRecipes(
            PreferencesSelection(
                selectedCuisines = setOf(Cuisine.ITALIAN, Cuisine.ASIAN),
                selectedDiets = setOf(Diet.VEGAN),
                selectedIntolerances = setOf(Intolerance.GLUTEN),
                selectedMealTypes = setOf(MealType.SOUP, MealType.BREAD)
            )
        )

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.TOO_MANY_REQUESTS, error)
    }
}