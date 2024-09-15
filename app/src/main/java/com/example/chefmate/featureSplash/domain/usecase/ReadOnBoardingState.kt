package com.example.chefmate.featureSplash.domain.usecase

import com.example.chefmate.core.data.repository.DataStoreRepository
import com.example.chefmate.core.presentation.util.Screen
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingState(
    private val repository: DataStoreRepository
) {
    operator fun invoke() : Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}