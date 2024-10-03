package com.example.chefmate.core.domain.usecase

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.DietPreferences
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.data.repository.FakeDataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SaveDietPreferencesTest {

    private lateinit var saveDietPreferences: SaveDietPreferences
    private lateinit var dataStoreRepository: FakeDataStoreRepository

    @Before
    fun setUp() {
        dataStoreRepository = FakeDataStoreRepository()
        saveDietPreferences = SaveDietPreferences(dataStoreRepository)
    }

    @Test
    fun `Correctly save diet preferences`() = runBlocking {
        val cuisines = listOf(
            Cuisine.THAI.displayName,
            Cuisine.ASIAN.displayName
        )
        val diets = emptyList<String>()
        val intolerances = listOf(
            Intolerance.DAIRY.displayName,
            Intolerance.WHEAT.displayName,
            Intolerance.SHELLFISH.displayName
        )
        val dietPreferences = DietPreferences(
            cuisines = cuisines,
            diets = diets,
            intolerances = intolerances
        )
        saveDietPreferences(dietPreferences)

        val retrievedCuisines = dataStoreRepository.getDietPreferences().first().cuisines
        val retrievedDiets = dataStoreRepository.getDietPreferences().first().diets
        val retrievedIntolerances = dataStoreRepository.getDietPreferences().first().intolerances

        assertEquals(cuisines, retrievedCuisines)
        assertEquals(diets, retrievedDiets)
        assertEquals(intolerances, retrievedIntolerances)
    }
}