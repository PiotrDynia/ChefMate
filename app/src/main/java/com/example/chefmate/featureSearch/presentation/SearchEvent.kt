package com.example.chefmate.featureSearch.presentation

sealed class SearchEvent {
    data class OnSearchInputChange(val input: String) : SearchEvent()
    data class OnCuisineSelected(val cuisine: String) : SearchEvent()
    data class OnExcludeCuisineSelected(val cuisine: String) : SearchEvent()
    data class OnDietSelected(val diet: String) : SearchEvent()
    data class OnIntoleranceSelected(val intolerance: String) : SearchEvent()
    data class OnMealTypeSelected(val mealType: String) : SearchEvent()
    data class OnSortTypeSelected(val sortType: String) : SearchEvent()
    data class OnMinCaloriesTextChange(val minCalories: Int) : SearchEvent()
    data class OnMaxCaloriesTextChange(val maxCalories: Int) : SearchEvent()
    data class OnCaloriesSliderPositionChange(val range: ClosedFloatingPointRange<Float>) : SearchEvent()
}