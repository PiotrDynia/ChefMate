package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.DietPreferences
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.data.repository.FakeDataStoreRepository
import com.example.chefmate.featureOnboarding.domain.usecase.SaveDietPreferences
import kotlinx.coroutines.flow.first
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