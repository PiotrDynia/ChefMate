package com.example.chefmate.featureSplash.domain.usecase

import com.example.chefmate.data.repository.FakeDataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ReadOnBoardingStateTest {
    private lateinit var readOnBoardingState: ReadOnBoardingState
    private lateinit var dataStoreRepository: FakeDataStoreRepository

    @Before
    fun setUp() {
        dataStoreRepository = FakeDataStoreRepository()
        readOnBoardingState = ReadOnBoardingState(dataStoreRepository)
    }

    @Test
    fun `Get correct onBoardingState`() = runBlocking {
        dataStoreRepository.saveOnBoardingState()

        val onboardingState = readOnBoardingState().first()

        assertTrue(onboardingState)
    }
}