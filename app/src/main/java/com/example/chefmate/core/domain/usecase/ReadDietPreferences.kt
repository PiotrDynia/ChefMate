package com.example.chefmate.core.domain.usecase

import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import kotlinx.coroutines.flow.first

class ReadDietPreferences(
    private val repository: DataStoreRepository
) {

    suspend operator fun invoke() : UserPreferencesResult {
        val preferences = repository.getDietPreferences().first()

        val selectedCuisines = Cuisine.entries.filter { cuisine ->
            preferences.cuisines.contains(cuisine.displayName)
        }.toSet()
        val selectedDiets = Diet.entries.filter { diet ->
            preferences.diets.contains(diet.displayName)
        }.toSet()
        val selectedIntolerances = Intolerance.entries.filter { intolerance ->
            preferences.intolerances.contains(intolerance.displayName)
        }.toSet()

        return UserPreferencesResult(
            selectedCuisines = selectedCuisines,
            selectedDiets = selectedDiets,
            selectedIntolerances = selectedIntolerances
        )
    }
}

data class UserPreferencesResult(
    val selectedCuisines: Set<Cuisine>,
    val selectedDiets: Set<Diet>,
    val selectedIntolerances: Set<Intolerance>
)
