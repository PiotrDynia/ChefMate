package com.example.chefmate.featureOnboarding.domain.usecase

import com.example.chefmate.data.repository.FakeDataStoreRepository
import com.example.chefmate.featureSplash.domain.usecase.ReadOnBoardingState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SaveOnBoardingStateTest {
    private lateinit var saveOnBoardingState: SaveOnBoardingState
    private lateinit var dataStoreRepository: FakeDataStoreRepository

    @Before
    fun setUp() {
        dataStoreRepository = FakeDataStoreRepository()
        saveOnBoardingState = SaveOnBoardingState(dataStoreRepository)
    }

    @Test
    fun `Correctly save onBoardingState`() = runBlocking {
        saveOnBoardingState()

        val onboardingState = dataStoreRepository.readOnBoardingState().first()

        assertTrue(onboardingState)
    }
}