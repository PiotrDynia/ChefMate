package com.example.chefmate.featureHome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.featureHome.domain.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
            loadUserPreferences()
            handleRecipeRecommendations()
        }
    }

    private suspend fun loadUserPreferences() {
        val preferences = useCases.readDietPreferences()

        _state.update {
            it.copy(
                selectedCuisines = preferences.selectedCuisines,
                selectedDiets = preferences.selectedDiets,
                selectedIntolerances = preferences.selectedIntolerances
            )
        }
    }

    private suspend fun handleRecipeRecommendations() {
        val selectedItemsFlow = createFlowFromSelectedItems()

        selectedItemsFlow.collectLatest { (cuisines, diets, intolerances) ->
            if (arePreferencesEmpty(cuisines, diets, intolerances)) {
                loadRandomRecipes()
            } else {
                val recommendedRecipes = fetchRecommendedRecipes(cuisines, diets, intolerances)

                when (recommendedRecipes) {
                    is Result.Success -> {
                        val recipes = recommendedRecipes.data
                        if (recipes.results.isEmpty()) {
                            setErrorMessage(R.string.can_t_find_any_recommendations_try_changing_your_filters)
                        } else {
                            updateRecommendations(recipes)
                        }
                    }

                    is Result.Error -> {
                        setErrorMessage(recommendedRecipes.error.messageResId)
                    }
                }
            }
        }
    }

    private fun createFlowFromSelectedItems() : Flow<Triple<Set<Cuisine>, Set<Diet>, Set<Intolerance>>> {
        return _state.map { state ->
            Triple(state.selectedCuisines, state.selectedDiets, state.selectedIntolerances)
        }.distinctUntilChanged()
    }

    private fun loadRandomRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val randomRecipes = useCases.fetchRandomRecipes()

            _state.update {
                it.copy(
                    recommendations = randomRecipes.recipes,
                    isLoading = false
                )
            }
        }
    }

    private suspend fun fetchRecommendedRecipes(
        cuisines: Set<Cuisine>,
        diets: Set<Diet>,
        intolerances: Set<Intolerance>
    ): Result<GetRecipeResult, DataError.Network> {
        return useCases.fetchRecipes(cuisines, diets, intolerances)
    }

    private fun updateRecommendations(recipes: GetRecipeResult) {
        _state.update {
            it.copy(
                recommendations = recipes.results,
                isLoading = false
            )
        }
    }

    private fun setErrorMessage(errorMessageResId: Int) {
        _state.update {
            it.copy(
                errorMessageResId = errorMessageResId
            )
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
        _state.update { it.copy(searchInput = input) }
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

    private fun arePreferencesEmpty(cuisines: Set<Cuisine>, diets: Set<Diet>, intolerances: Set<Intolerance>): Boolean {
        return cuisines.isEmpty() && diets.isEmpty() && intolerances.isEmpty()
    }
}