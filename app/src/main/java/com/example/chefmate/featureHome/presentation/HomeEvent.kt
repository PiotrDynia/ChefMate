package com.example.chefmate.featureHome.presentation

import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.core.domain.util.userPreferences.MealType

sealed class HomeEvent {
    data class OnSearchInputChange(val input: String) : HomeEvent()
    data class OnCuisineSelected(val cuisine: Cuisine) : HomeEvent()
    data class OnDietSelected(val diet: Diet) : HomeEvent()
    data class OnIntoleranceSelected(val intolerance: Intolerance) : HomeEvent()
    data class OnMealTypeSelected(val mealType: MealType) : HomeEvent()
    data object OnDismissAutocomplete : HomeEvent()
    data object OnAdvancedSearchClick : HomeEvent()
    data class OnRecipeClick(val id: Int) : HomeEvent()
}