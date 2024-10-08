package com.example.chefmate.featureSearch.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnSearchInputChange -> onSearchInputChange(event.input)
            is SearchEvent.OnCuisineSelected -> onCuisineSelected(event.cuisine)
            is SearchEvent.OnDietSelected -> onDietSelected(event.diet)
            is SearchEvent.OnExcludeCuisineSelected -> onExcludeCuisineSelected(event.cuisine)
            is SearchEvent.OnIntoleranceSelected -> onIntoleranceSelected(event.intolerance)
            is SearchEvent.OnMealTypeSelected -> onMealTypeSelected(event.mealType)
            is SearchEvent.OnSortTypeSelected -> onSortTypeSelected(event.sortType)
            is SearchEvent.OnCaloriesSliderPositionChange -> onCaloriesSliderPositionChange(event.range)
            is SearchEvent.OnServingsSliderPositionChange -> onSliderSliderPositionChange(event.range)
        }
    }

    private fun onSearchInputChange(input: String) {
        _state.update {
            it.copy(searchInput = input)
        }
    }

    private fun onCuisineSelected(cuisine: String) {
        _state.update { currentState ->
            val updatedCuisines = if (currentState.selectedCuisines.contains(cuisine)) {
                currentState.selectedCuisines - cuisine
            } else {
                currentState.selectedCuisines + cuisine
            }
            currentState.copy(selectedCuisines = updatedCuisines)
        }
    }

    private fun onDietSelected(diet: String) {
        _state.update { currentState ->
            val updatedDiets = if (currentState.selectedDiets.contains(diet)) {
                currentState.selectedDiets - diet
            } else {
                currentState.selectedDiets + diet
            }
            currentState.copy(selectedDiets = updatedDiets)
        }
    }

    private fun onExcludeCuisineSelected(cuisine: String) {
        _state.update { currentState ->
            val excludedCuisines = if (currentState.excludedCuisines.contains(cuisine)) {
                currentState.excludedCuisines - cuisine
            } else {
                currentState.excludedCuisines + cuisine
            }
            currentState.copy(excludedCuisines = excludedCuisines)
        }
    }

    private fun onIntoleranceSelected(intolerance: String) {
        _state.update { currentState ->
            val updatedIntolerances = if (currentState.selectedIntolerances.contains(intolerance)) {
                currentState.selectedIntolerances - intolerance
            } else {
                currentState.selectedIntolerances + intolerance
            }
            currentState.copy(selectedIntolerances = updatedIntolerances)
        }
    }

    private fun onMealTypeSelected(mealType: String) {
        _state.update { currentState ->
            val updatedMealTypes = if (currentState.selectedMealTypes.contains(mealType)) {
                currentState.selectedMealTypes - mealType
            } else {
                currentState.selectedMealTypes + mealType
            }
            currentState.copy(selectedMealTypes = updatedMealTypes)
        }
    }

    private fun onSortTypeSelected(sortType: String) {
        _state.update { it.copy(selectedSortType = sortType) }
    }

    private fun onCaloriesSliderPositionChange(value: ClosedFloatingPointRange<Float>) {
        _state.update {
            it.copy(caloriesSliderPosition = value)
        }
    }

    private fun onSliderSliderPositionChange(value: ClosedFloatingPointRange<Float>) {
        _state.update {
            it.copy(servingsSliderPosition = value)
        }
    }
}