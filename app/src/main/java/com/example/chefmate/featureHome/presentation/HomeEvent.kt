package com.example.chefmate.featureHome.presentation

import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.MealType

sealed class HomeEvent {
    data class OnSearchInputChange(val input: String) : HomeEvent()
    data class OnCuisineSelected(val cuisine: Cuisine) : HomeEvent()
    data class OnDietSelected(val diet: Diet) : HomeEvent()
    data class OnIntoleranceSelected(val intolerance: Intolerance) : HomeEvent()
    data class OnMealTypeSelected(val mealType: MealType) : HomeEvent()
    data object OnDismissAutocomplete : HomeEvent()
    data object OnAutocompleteItemClick : HomeEvent()
}