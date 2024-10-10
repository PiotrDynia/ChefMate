package com.example.chefmate.data.repository

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.userPreferences.DietPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDataStoreRepository : DataStoreRepository {

    private val preferencesMap = mutableMapOf<String, String>()
    private val onBoardingStateFlow = MutableStateFlow(false)

    companion object {
        const val DIET_PREFERENCES_KEY = "diet_preferences"
        const val CUISINE_PREFERENCES_KEY = "cuisine_preferences"
        const val INTOLERANCES_KEY = "intolerances"
    }

    override suspend fun saveOnBoardingState() {
        onBoardingStateFlow.value = true
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return onBoardingStateFlow
    }

    override suspend fun saveDietPreferences(dietPreferences: DietPreferences) {
        preferencesMap[DIET_PREFERENCES_KEY] = if (dietPreferences.diets.isEmpty()) "" else dietPreferences.diets.joinToString(",")
        preferencesMap[CUISINE_PREFERENCES_KEY] = if (dietPreferences.cuisines.isEmpty()) "" else dietPreferences.cuisines.joinToString(",")
        preferencesMap[INTOLERANCES_KEY] = if (dietPreferences.intolerances.isEmpty()) "" else dietPreferences.intolerances.joinToString(",")
    }

    override fun getDietPreferences(): Flow<DietPreferences> {
        return MutableStateFlow(
            DietPreferences(
                diets = preferencesMap[DIET_PREFERENCES_KEY]?.takeIf { it.isNotEmpty() }?.split(",") ?: emptyList(),
                cuisines = preferencesMap[CUISINE_PREFERENCES_KEY]?.takeIf { it.isNotEmpty() }?.split(",") ?: emptyList(),
                intolerances = preferencesMap[INTOLERANCES_KEY]?.takeIf { it.isNotEmpty() }?.split(",") ?: emptyList()
            )
        )
    }
}