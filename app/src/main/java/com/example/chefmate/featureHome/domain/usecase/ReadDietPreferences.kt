package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.DietPreferences
import kotlinx.coroutines.flow.Flow

class ReadDietPreferences(
    private val repository: DataStoreRepository
) {

    operator fun invoke() : Flow<DietPreferences> {
        return repository.getDietPreferences()
    }
}
