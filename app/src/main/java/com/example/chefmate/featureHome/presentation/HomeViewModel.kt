package com.example.chefmate.featureHome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.DietPreferences
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.core.domain.util.userPreferences.MealType
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureHome.domain.usecase.HomeUseCases
import com.example.chefmate.featureHome.domain.util.PreferencesSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadUserPreferences()
            withContext(Dispatchers.Main) {
                handleRecipeRecommendations()
            }
        }
    }

    private suspend fun loadUserPreferences() {
        val preferences = useCases.readDietPreferences()

        withContext(Dispatchers.Main) {
            _state.update {
                it.copy(
                    selectedCuisines = preferences.selectedCuisines,
                    selectedDiets = preferences.selectedDiets,
                    selectedIntolerances = preferences.selectedIntolerances
                )
            }
        }
    }

    private suspend fun handleRecipeRecommendations() {
        val selectedItemsFlow = createFlowFromSelectedItems()

        selectedItemsFlow.collectLatest { preferences ->
            if (arePreferencesEmpty(preferences)) {
                loadRandomRecipes()
            } else {
                fetchRecommendedRecipes(preferences)
            }
        }
    }

    private fun createFlowFromSelectedItems(): Flow<PreferencesSelection> {
        return _state.map { state ->
            PreferencesSelection(
                selectedCuisines = state.selectedCuisines,
                selectedDiets = state.selectedDiets,
                selectedIntolerances = state.selectedIntolerances,
                selectedMealTypes = state.selectedMealTypes
            )
        }.distinctUntilChanged()
    }

    private fun arePreferencesEmpty(preferencesSelection: PreferencesSelection): Boolean {
        return preferencesSelection.selectedCuisines.isEmpty() &&
                preferencesSelection.selectedDiets.isEmpty() &&
                preferencesSelection.selectedIntolerances.isEmpty() &&
                preferencesSelection.selectedMealTypes.isEmpty()
    }

    private suspend fun loadRandomRecipes() {
        when (val result = useCases.fetchRandomRecipes()) {
            is Result.Success -> updateRecommendations(result.data.recipes)
            is Result.Error -> setErrorMessage(result.error.getErrorMessageResId())
        }
    }

    private suspend fun fetchRecommendedRecipes(preferencesSelection: PreferencesSelection) {
        when (val result = useCases.fetchRecipes(preferencesSelection)) {
            is Result.Success -> handleFetchedRecommendations(result.data.results)
            is Result.Error -> setErrorMessage(result.error.getErrorMessageResId())
        }
    }

    private fun handleFetchedRecommendations(recipes: List<RecipeSimple>) {
        if (recipes.isEmpty()) {
            setErrorMessage(R.string.can_t_find_any_recommendations_try_changing_your_filters)
        } else {
            updateRecommendations(recipes)
        }
    }

    private fun updateRecommendations(recipes: List<RecipeSimple>) {
        _state.update {
            it.copy(
                recommendations = recipes,
                isLoading = false
            )
        }
    }

    private fun setErrorMessage(errorMessageResId: Int) {
        _state.update {
            it.copy(
                errorMessageResId = errorMessageResId,
                isLoading = false
            )
        }
    }

    private fun setAutocompleteErrorMessage(errorMessageResId: Int) {
        _state.update {
            it.copy(
                autocompleteErrorMessageResId = errorMessageResId,
                isSearchAutocompleteExpanded = true,
                isLoading = false
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchInputChange -> {
                changeSearchInput(event.input)
                handleAutocomplete(event.input)
            }
            is HomeEvent.OnCuisineSelected -> onCuisineSelected(event.cuisine)
            is HomeEvent.OnDietSelected -> onDietSelected(event.diet)
            is HomeEvent.OnIntoleranceSelected -> onIntoleranceSelected(event.intolerance)
            is HomeEvent.OnMealTypeSelected -> onMealTypeSelected(event.mealType)
            HomeEvent.OnDismissAutocomplete -> onDismissAutocomplete()
            HomeEvent.OnAdvancedSearchClick -> navigateToSearch()
            is HomeEvent.OnRecipeClick -> navigateToRecipeDetails(event.id)
        }
    }

    private fun navigateToRecipeDetails(id: Int) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Screen.Details.route + "?id=$id"))
        }
    }

    private fun navigateToSearch() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Screen.Search.route))
        }
    }

    private fun changeSearchInput(input: String) {
        _state.update { it.copy(searchInput = input) }
    }

    private fun handleAutocomplete(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCases.getAutocompleteRecipes(input)) {
                is Result.Success -> updateAutocompleteState(result.data)
                is Result.Error -> setAutocompleteErrorMessage(result.error.getErrorMessageResId())
            }
        }
    }

    private fun updateAutocompleteState(result: ArrayList<GetRecipesAutocompleteResultItem>) {
        _state.update {
            it.copy(
                isSearchAutocompleteExpanded = result.isNotEmpty(),
                autocompletedResults = result
            )
        }
    }

    private fun onCuisineSelected(cuisine: Cuisine) {
        _state.update { currentState ->
            val updatedCuisines = if (currentState.selectedCuisines.contains(cuisine)) {
                currentState.selectedCuisines - cuisine
            } else {
                currentState.selectedCuisines + cuisine
            }
            val currentDiets = currentState.selectedDiets
            val currentIntolerances = currentState.selectedIntolerances
            viewModelScope.launch {
                useCases.saveDietPreferences(
                    DietPreferences(
                        diets = currentDiets.map { it.displayName },
                        cuisines = updatedCuisines.map { it.displayName },
                        intolerances = currentIntolerances.map { it.displayName }
                    )
                )
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
            val currentCuisines = currentState.selectedCuisines
            val currentIntolerances = currentState.selectedIntolerances
            viewModelScope.launch {
                useCases.saveDietPreferences(
                    DietPreferences(
                        diets = updatedDiets.map { it.displayName },
                        cuisines = currentCuisines.map { it.displayName },
                        intolerances = currentIntolerances.map { it.displayName }
                    )
                )
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
            val currentCuisines = currentState.selectedCuisines
            val currentDiets = currentState.selectedDiets
            viewModelScope.launch {
                useCases.saveDietPreferences(
                    DietPreferences(
                        diets = currentDiets.map { it.displayName },
                        cuisines = currentCuisines.map { it.displayName },
                        intolerances = updatedIntolerances.map { it.displayName }
                    )
                )
            }
            currentState.copy(selectedIntolerances = updatedIntolerances)
        }
    }

    private fun onMealTypeSelected(mealType: MealType) {
        _state.update { currentState ->
            val updatedMealTypes = if (currentState.selectedMealTypes.contains(mealType)) {
                currentState.selectedMealTypes - mealType
            } else {
                currentState.selectedMealTypes + mealType
            }
            currentState.copy(selectedMealTypes = updatedMealTypes)
        }
    }

    private fun onDismissAutocomplete() {
        _state.update {
            it.copy(isSearchAutocompleteExpanded = false)
        }
    }
}