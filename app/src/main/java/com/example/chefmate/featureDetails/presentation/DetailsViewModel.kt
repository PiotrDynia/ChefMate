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
import kotlinx.coroutines.withContext
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
            loadRecipeDetails(recipeId)
            checkIfRecipeIsBookmarked()
        }
    }

    private suspend fun loadRecipeDetails(recipeId: Int) {
        val details = useCases.getRecipeDetailsFromAPI(recipeId)
        _state.update {
            it.copy(
                details = details,
                isLoading = false
            )
        }
    }

    private suspend fun checkIfRecipeIsBookmarked() {
        val isBookmarked = state.value.details?.id?.let { recipeId ->
            useCases.checkRecipeIsBookmarked(recipeId)
        } ?: false
        _state.value = state.value.copy(isBookmarked = isBookmarked)
    }

    fun onEvent(event: DetailsEvent) {
        when(event) {
            DetailsEvent.OnBackClick -> navigateBack()
            DetailsEvent.OnBookmarkClick -> onBookmarkClick()
        }
    }

    private fun onBookmarkClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeDetails = state.value.details
            recipeDetails?.let { recipe ->
                if (state.value.isBookmarked) {
                    val cachedRecipe = useCases.getRecipeFromCache(recipe.id)
                    useCases.deleteRecipeFromCache(cachedRecipe.recipe.id, cachedRecipe.recipe.imagePath)
                } else {
                    useCases.saveRecipe(recipeDetails)
                }
                withContext(Dispatchers.Main) {
                    _state.value = state.value.copy(isBookmarked = !state.value.isBookmarked)
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.PopBackStack)
        }
    }
}