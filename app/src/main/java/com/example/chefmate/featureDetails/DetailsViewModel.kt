package com.example.chefmate.featureDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: DetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipeDetails = MutableStateFlow<RecipeDetails?>(null)
    val recipeDetails: StateFlow<RecipeDetails?> = _recipeDetails

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeId = savedStateHandle.get<Int>("id") ?: return@launch
            _recipeDetails.value = useCases.getRecipeDetails(recipeId)
        }
    }
}