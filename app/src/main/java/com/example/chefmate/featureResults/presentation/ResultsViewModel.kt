package com.example.chefmate.featureResults.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureSearch.presentation.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor() : ViewModel() {
    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    fun getFilterSets(searchState: SearchState): List<Pair<Int, Int>> {
        return listOf(
            R.string.cuisine to searchState.selectedCuisines.size,
            R.string.excluded_cuisine to searchState.excludedCuisines.size,
            R.string.diet to searchState.selectedDiets.size,
            R.string.intolerance to searchState.selectedIntolerances.size,
            R.string.meal_type to searchState.selectedMealTypes.size
        )
    }

    fun onFilterChipClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Screen.Search.route))
        }
    }

    fun onRecipeClick(id: Int) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Screen.Details.route + "?id=$id"))
        }
    }
}