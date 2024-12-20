package com.example.chefmate.featureSearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.Error
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureHome.presentation.HomeState
import com.example.chefmate.featureSearch.domain.usecase.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: SearchUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    fun loadUserPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            val userDietPreferences = useCases.readDietPreferences()
            withContext(Dispatchers.Main) {
                _state.update { state ->
                    state.copy(
                        selectedDiets = userDietPreferences.selectedDiets.map { it.displayName }
                            .toSet(),
                        selectedCuisines = userDietPreferences.selectedCuisines.map { it.displayName }
                            .toSet(),
                        selectedIntolerances = userDietPreferences.selectedIntolerances.map { it.displayName }
                            .toSet(),
                        areUserPreferencesLoaded = true
                    )
                }
            }
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchInputChange -> onSearchInputChange(event.input)
            is SearchEvent.OnCuisineSelected -> onCuisineSelected(event.cuisine)
            is SearchEvent.OnDietSelected -> onDietSelected(event.diet)
            is SearchEvent.OnExcludeCuisineSelected -> onExcludeCuisineSelected(event.cuisine)
            is SearchEvent.OnIntoleranceSelected -> onIntoleranceSelected(event.intolerance)
            is SearchEvent.OnMealTypeSelected -> onMealTypeSelected(event.mealType)
            is SearchEvent.OnSortTypeSelected -> onSortTypeSelected(event.sortType)
            is SearchEvent.OnCaloriesSliderPositionChange -> onCaloriesSliderPositionChange(event.range)
            is SearchEvent.OnServingsSliderPositionChange -> onServingsSliderPositionChange(event.range)
            SearchEvent.OnSearchClick -> searchRecipes()
            is SearchEvent.OnHomeSearchClick -> searchFromHomeSearchBar(event.homeUserPreferences)
        }
    }

    private fun searchFromHomeSearchBar(homeState: HomeState) {
        _state.update { state ->
            state.copy(
                searchInput = homeState.searchInput,
                selectedCuisines = homeState.selectedCuisines.map { it.displayName }.toSet(),
                selectedDiets = homeState.selectedDiets.map { it.displayName }.toSet(),
                selectedIntolerances = homeState.selectedIntolerances.map { it.displayName }
                    .toSet(),
                selectedMealTypes = homeState.selectedMealTypes.map { it.displayName }.toSet(),
            )
        }
        searchRecipes()
    }

    private fun searchRecipes() {
        _state.update {
            it.copy(areSearchResultsLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCases.searchRecipes(_state.value)) {
                is Result.Success -> loadResults(result.data.results)
                is Result.Error -> showErrorSnackbar(result.error)
            }
        }
    }

    private suspend fun loadResults(results: List<RecipeSimple>) {
        _uiEvent.send(UiEvent.Navigate(route = Screen.Results.route))
        _state.update {
            it.copy(
                searchResults = results,
                areSearchResultsLoading = false
            )
        }
    }

    private suspend fun showErrorSnackbar(error: Error) {
        _uiEvent.send(UiEvent.ShowSnackbar(error.getErrorMessageResId()))
        _state.update { it.copy(areSearchResultsLoading = false) }
    }

    private fun onSearchInputChange(input: String) {
        _state.update { it.copy(searchInput = input) }
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
        _state.update { it.copy(caloriesSliderPosition = value) }
    }

    private fun onServingsSliderPositionChange(value: ClosedFloatingPointRange<Float>) {
        _state.update { it.copy(servingsSliderPosition = value) }
    }
}