package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.ValidationError
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchRecipesTest {
    private lateinit var searchRecipes: SearchRecipes

    @Before
    fun setUp() {
        searchRecipes = SearchRecipes()
    }

    @Test
    fun `Should search for recipes successfully`() = runBlocking {
        val result = searchRecipes(query = "Dummy query")

        assertTrue(result is Result.Success)
    }

    @Test
    fun `Should return error for empty query`() = runBlocking {
        val result = searchRecipes(query = "")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(ValidationError.EMPTY, error)
    }

    @Test
    fun `Should return error for too short query`() = runBlocking {
        val result = searchRecipes(query = "Aw")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(ValidationError.TOO_SHORT, error)
    }
}