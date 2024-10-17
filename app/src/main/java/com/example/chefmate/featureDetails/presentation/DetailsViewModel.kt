package com.example.chefmate.featureDetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: DetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeId = savedStateHandle.get<Int>("id") ?: return@launch
            val details = useCases.getRecipeDetails(recipeId)
            _state.update {
                it.copy(
                    details = details,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: DetailsEvent) {
        when(event) {
            DetailsEvent.OnBackClick -> navigateBack()
            DetailsEvent.OnBookmarkClick -> bookmarkRecipe()
        }
    }

    private fun bookmarkRecipe() {
        // TODO cache in database
        println(_state.value.isBookmarked)
        _state.update { it.copy(isBookmarked = !it.isBookmarked) }
        println(_state.value.isBookmarked)
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.PopBackStack)
        }
    }
}