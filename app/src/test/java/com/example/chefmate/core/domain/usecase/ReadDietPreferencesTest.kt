package com.example.chefmate.core.domain.usecase

import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.DietPreferences
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.data.repository.FakeDataStoreRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ReadDietPreferencesTest {

    private lateinit var readDietPreferences: ReadDietPreferences
    private lateinit var dataStoreRepository: FakeDataStoreRepository

    @Before
    fun setUp() {
        dataStoreRepository = FakeDataStoreRepository()
        readDietPreferences = ReadDietPreferences(dataStoreRepository)
    }

    @Test
    fun `Correctly read diet preferences`() = runBlocking {
        val cuisines = setOf(
            Cuisine.THAI,
            Cuisine.ASIAN
        )
        val diets = emptySet<Diet>()
        val intolerances = setOf(
            Intolerance.DAIRY,
            Intolerance.WHEAT,
            Intolerance.SHELLFISH
        )
        val preferences = DietPreferences(
            cuisines = cuisines.map { it.displayName },
            diets = diets.map { it.displayName },
            intolerances = intolerances.map { it.displayName }
        )
        dataStoreRepository.saveDietPreferences(preferences)

        val retrievedCuisines = readDietPreferences().selectedCuisines
        val retrievedDiets = readDietPreferences().selectedDiets
        val retrievedIntolerances = readDietPreferences().selectedIntolerances

        assertEquals(cuisines, retrievedCuisines)
        assertEquals(diets, retrievedDiets)
        assertEquals(intolerances, retrievedIntolerances)
    }
}