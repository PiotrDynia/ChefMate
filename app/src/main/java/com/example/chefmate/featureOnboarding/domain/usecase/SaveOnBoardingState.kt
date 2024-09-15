package com.example.chefmate.featureOnboarding.domain.usecase

import com.example.chefmate.core.data.repository.DataStoreRepository

class SaveOnBoardingState(
    private val repository: DataStoreRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}