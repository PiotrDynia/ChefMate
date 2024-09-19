package com.example.chefmate.featureHome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.featureHome.domain.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.readDietPreferences().collect{ preferences ->
                val selectedCuisines = Cuisine.entries.filter { cuisine ->
                    preferences.cuisines.contains(cuisine.displayName)
                }.toSet()
                val selectedDiets = Diet.entries.filter { diet ->
                    preferences.diets.contains(diet.displayName)
                }.toSet()
                val selectedIntolerances = Intolerance.entries.filter { intolerance ->
                    preferences.intolerances.contains(intolerance.displayName)
                }.toSet()
                _state.update { currentState ->
                    currentState.copy(
                        selectedCuisines = selectedCuisines,
                        selectedDiets = selectedDiets,
                        selectedIntolerances = selectedIntolerances,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchInputChange -> changeSearchInput(event.input)
            is HomeEvent.OnCuisineSelected -> onCuisineSelected(event.cuisine)
            is HomeEvent.OnDietSelected -> onDietSelected(event.diet)
            is HomeEvent.OnIntoleranceSelected -> onIntoleranceSelected(event.intolerance)
        }
    }

    private fun changeSearchInput(input: String) {
        _state.update { currentState ->
            currentState.copy(searchInput = input)
        }
    }

    private fun onCuisineSelected(cuisine: Cuisine) {
        _state.update { currentState ->
            val updatedCuisines = if (currentState.selectedCuisines.contains(cuisine)) {
                currentState.selectedCuisines - cuisine
            } else {
                currentState.selectedCuisines + cuisine
            }

            currentState.copy(selectedCuisines = updatedCuisines)
        }
    }

    private fun onDietSelected(diet: Diet) {
        _state.update { currentState ->
            val updatedDiets = if (currentState.selectedDiets.contains(diet)) {
                currentState.selectedDiets - diet
            } else {
                currentState.selectedDiets + diet
            }
            currentState.copy(selectedDiets = updatedDiets)
        }
    }

    private fun onIntoleranceSelected(intolerance: Intolerance) {
        _state.update { currentState ->
            val updatedIntolerances = if (currentState.selectedIntolerances.contains(intolerance)) {
                currentState.selectedIntolerances - intolerance
            } else {
                currentState.selectedIntolerances + intolerance
            }
            currentState.copy(selectedIntolerances = updatedIntolerances)
        }
    }
}