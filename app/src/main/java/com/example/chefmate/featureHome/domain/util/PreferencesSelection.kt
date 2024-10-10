package com.example.chefmate.featureHome.domain.util

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.MealType

data class PreferencesSelection(
    val selectedCuisines: Set<Cuisine>,
    val selectedDiets: Set<Diet>,
    val selectedIntolerances: Set<Intolerance>,
    val selectedMealTypes: Set<MealType>
)