package com.example.chefmate.featureSplash.domain.usecase

import com.example.chefmate.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingState(
    private val repository: DataStoreRepository
) {
    operator fun invoke() : Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}