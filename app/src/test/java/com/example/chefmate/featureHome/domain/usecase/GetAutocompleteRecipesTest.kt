package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.featureHome.data.repository.FakeHomeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAutocompleteRecipesTest {
    private lateinit var repository: FakeHomeRepository
    private lateinit var getAutocompleteRecipes: GetAutocompleteRecipes

    @Before
    fun setUp() {
        repository = FakeHomeRepository()
        getAutocompleteRecipes = GetAutocompleteRecipes(repository)
    }

    @Test
    fun `Should return autocompleted recipes successfully`() = runBlocking {
        val fakeRecipes = ArrayList<GetRecipesAutocompleteResultItem>().apply {
            add(GetRecipesAutocompleteResultItem(id = 1, title = "Autocompleted recipe 1"))
            add(GetRecipesAutocompleteResultItem(id = 2, title = "Autocompleted recipe 2"))
        }
        repository.autocompleteResults = fakeRecipes

        val result = getAutocompleteRecipes(input = "Recipe")

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(2, data.size)
        assertEquals("Autocompleted recipe 1", data[0].title)
    }

    @Test
    fun `Should return empty result on short input`() = runBlocking {
        val fakeRecipes = ArrayList<GetRecipesAutocompleteResultItem>().apply {
            GetRecipesAutocompleteResultItem(id = 1, title = "Autocompleted recipe 1")
            GetRecipesAutocompleteResultItem(id = 2, title = "Autocompleted recipe 2")
        }
        repository.autocompleteResults = fakeRecipes

        val result = getAutocompleteRecipes(input = "Re")

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertTrue(data.isEmpty())
    }

    @Test
    fun `Should return error for server error`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 500

        val result = getAutocompleteRecipes(input = "Recipe")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.SERVER_ERROR, error)
    }

    @Test
    fun `Should return error for no internet`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 0

        val result = getAutocompleteRecipes(input = "Recipe")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.NO_INTERNET, error)
    }

    @Test
    fun `Should return error for too many requests`() = runBlocking {
        repository.shouldReturnError = true
        repository.httpErrorCode = 429

        val result = getAutocompleteRecipes(input = "Recipe")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Network.TOO_MANY_REQUESTS, error)
    }
}