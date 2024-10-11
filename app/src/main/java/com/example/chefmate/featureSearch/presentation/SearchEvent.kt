package com.example.chefmate.featureSearch.presentation

sealed class SearchEvent {
    data class OnSearchInputChange(val input: String) : SearchEvent()
    data class OnCuisineSelected(val cuisine: String) : SearchEvent()
    data class OnExcludeCuisineSelected(val cuisine: String) : SearchEvent()
    data class OnDietSelected(val diet: String) : SearchEvent()
    data class OnIntoleranceSelected(val intolerance: String) : SearchEvent()
    data class OnMealTypeSelected(val mealType: String) : SearchEvent()
    data class OnSortTypeSelected(val sortType: String) : SearchEvent()
    data class OnCaloriesSliderPositionChange(val range: ClosedFloatingPointRange<Float>) : SearchEvent()
    data class OnServingsSliderPositionChange(val range: ClosedFloatingPointRange<Float>) : SearchEvent()
    data class OnHomeScreenSearchClick(val searchFilters: SearchState) : SearchEvent()
    data object OnSearchClick : SearchEvent()
}