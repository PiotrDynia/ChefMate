package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRecipeDetailsFromAPITest {

    private lateinit var repository: FakeDetailsRepository
    private lateinit var getRecipeDetailsFromAPI: GetRecipeDetailsFromAPI

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        getRecipeDetailsFromAPI = GetRecipeDetailsFromAPI(repository)
    }

    @Test
    fun `Correctly get recipe details from API`() = runBlocking {
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
            extendedIngredients = emptyList()
        )
        repository.createFakeRemoteRecipe(dummyRecipe)
        val result = getRecipeDetailsFromAPI(1)
        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals("Dummy title", data.title)
    }
}