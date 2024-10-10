package com.example.chefmate.core.domain.usecase

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.userPreferences.DietPreferences

class SaveDietPreferences(
    private val repository: DataStoreRepository
) {

    suspend operator fun invoke(dietPreferences: DietPreferences) {
        return repository.saveDietPreferences(dietPreferences)
    }
}