package com.example.chefmate.featureDetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.data.api.dto.Ingredient
import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureDetails.domain.model.toRecipeDetails
import com.example.chefmate.featureDetails.domain.usecase.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            val isBookmarked = savedStateHandle.get<Boolean>("isBookmarked")!!
            if (isBookmarked) {
                loadRecipeFromCache(recipeId)
            } else {
                loadRecipeDetailsFromAPI(recipeId)
                checkIfRecipeIsBookmarked()
            }
        }
    }

    private suspend fun loadRecipeFromCache(recipeId: Int) {
        val cachedRecipe = useCases.getRecipeFromCache(recipeId)
        withContext(Dispatchers.Main) {
            _state.update {
                it.copy(
                    details = cachedRecipe.toRecipeDetails(),
                    isLoading = false,
                    isBookmarked = true
                )
            }
        }
    }

    private suspend fun loadRecipeDetailsFromAPI(recipeId: Int) {
        when (val result = useCases.getRecipeDetailsFromAPI(recipeId)) {
            is Result.Success -> loadDetailsOnSuccess(result.data)
            is Result.Error -> handleFetchError(result.error)
        }
    }

    private suspend fun loadDetailsOnSuccess(recipeDetails: RecipeDetails) {
        withContext(Dispatchers.Main) {
            _state.update {
                it.copy(
                    details = recipeDetails,
                    isLoading = false
                )
            }
        }
    }

    private suspend fun handleFetchError(error: DataError.Network) {
        _uiEvent.send(UiEvent.ShowSnackbar(error.getErrorMessageResId()))
        _uiEvent.send(UiEvent.PopBackStack)
    }

    private suspend fun checkIfRecipeIsBookmarked() {
        val isBookmarked = state.value.details?.id?.let { recipeId ->
            useCases.checkRecipeIsBookmarked(recipeId)
        } ?: false
        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(isBookmarked = isBookmarked)
        }
    }

    fun isIngredientInShoppingList(id: Int): StateFlow<Boolean> {
        return _state.value.ingredientListStatusMap.getOrPut(id) {
            MutableStateFlow(false).also { stateFlow ->
                viewModelScope.launch {
                    stateFlow.value = useCases.checkIngredientIsInShoppingList(id)
                }
            }
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.OnBackClick -> navigateBack()
            DetailsEvent.OnBookmarkClick -> onBookmarkClick()
            is DetailsEvent.OnAddIngredientToShoppingListClick -> addIngredientToShoppingList(event.ingredient)
            DetailsEvent.OnUndoAddIngredientToShoppingList -> undoAddIngredientToShoppingList()
            is DetailsEvent.OnDeleteIngredientFromShoppingList -> deleteIngredientFromShoppingList(event.ingredient)
            DetailsEvent.OnAddAllIngredientsToShoppingListClick -> addAllIngredientsToShoppingList()
        }
    }

    private fun addAllIngredientsToShoppingList() {
        viewModelScope.launch {
            val ingredients = state.value.details?.extendedIngredients ?: return@launch

            val ingredientsToAdd = ingredients.filter { ingredient ->
                !useCases.checkIngredientIsInShoppingList(ingredient.id)
            }

            if (ingredientsToAdd.isEmpty()) {
                _uiEvent.send(UiEvent.ShowSnackbar(R.string.all_items_are_already_added_to_shopping_list))
                return@launch
            }

            ingredientsToAdd.forEach { ingredient ->
                useCases.addShoppingListItem(ingredient, state.value.details!!.id)
            }

            withContext(Dispatchers.Main) {
                _state.update { currentState ->
                    val updatedListStatusMap = currentState.ingredientListStatusMap.toMutableMap()
                    ingredientsToAdd.forEach { ingredient ->
                        updatedListStatusMap[ingredient.id] = MutableStateFlow(true)
                    }
                    currentState.copy(ingredientListStatusMap = updatedListStatusMap)
                }
            }

            _uiEvent.send(UiEvent.ShowSnackbar(R.string.all_items_were_added_to_the_shopping_list))
        }
    }

    private fun addIngredientToShoppingList(ingredient: Ingredient) {
        viewModelScope.launch {
            useCases.addShoppingListItem(ingredient, _state.value.details!!.id)
            withContext(Dispatchers.Main) {
                _state.update { currentState ->
                    val updatedListStatusMap = currentState.ingredientListStatusMap.toMutableMap()
                    updatedListStatusMap[ingredient.id] = MutableStateFlow(true)

                    currentState.copy(
                        ingredientListStatusMap = updatedListStatusMap,
                        addedIngredient = ingredient
                    )
                }
            }
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    message = R.string.item_added_to_shopping_list,
                    action = R.string.undo
                )
            )
        }
    }

    private fun undoAddIngredientToShoppingList() {
        viewModelScope.launch {
            _state.value.addedIngredient?.let { ingredient ->
                useCases.deleteShoppingListItem(ingredient.id)

                withContext(Dispatchers.Main) {
                    _state.update { currentState ->
                        val updatedListStatusMap =
                            currentState.ingredientListStatusMap.toMutableMap()
                        updatedListStatusMap[ingredient.id]?.value = false

                        currentState.copy(
                            ingredientListStatusMap = updatedListStatusMap,
                            addedIngredient = null
                        )
                    }
                }
            }
        }
    }

    private fun deleteIngredientFromShoppingList(ingredient: Ingredient) {
        viewModelScope.launch {
            useCases.deleteShoppingListItem(ingredient.id)

            withContext(Dispatchers.Main) {
                _state.update { currentState ->
                    val updatedListStatusMap = currentState.ingredientListStatusMap.toMutableMap()
                    updatedListStatusMap[ingredient.id]?.value = false

                    currentState.copy(
                        ingredientListStatusMap = updatedListStatusMap
                    )
                }
            }
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    message = R.string.item_removed_from_shopping_list
                )
            )
        }
    }

    private fun onBookmarkClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeDetails = state.value.details
            recipeDetails?.let { recipe ->
                if (state.value.isBookmarked) {
                    val cachedRecipe = useCases.getRecipeFromCache(recipe.id)
                    useCases.deleteRecipeFromCache(
                        cachedRecipe.recipe.id,
                        cachedRecipe.recipe.imagePath
                    )
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