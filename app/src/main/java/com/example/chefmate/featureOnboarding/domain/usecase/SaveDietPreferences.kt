package com.example.chefmate.featureOnboarding.domain.usecase

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.DietPreferences

class SaveDietPreferences(
    private val repository: DataStoreRepository
) {

    suspend operator fun invoke(dietPreferences: DietPreferences) {
        return repository.saveDietPreferences(dietPreferences)
    }
}