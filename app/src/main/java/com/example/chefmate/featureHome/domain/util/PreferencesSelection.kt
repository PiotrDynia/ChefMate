package com.example.chefmate.featureHome.domain.util

import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.core.domain.util.userPreferences.MealType

data class PreferencesSelection(
    val selectedCuisines: Set<Cuisine>,
    val selectedDiets: Set<Diet>,
    val selectedIntolerances: Set<Intolerance>,
    val selectedMealTypes: Set<MealType>
)