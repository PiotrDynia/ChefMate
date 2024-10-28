package com.example.chefmate.featureShoppingList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureDetails.domain.model.toIngredient
import com.example.chefmate.featureShoppingList.domain.usecase.ShoppingListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val useCases: ShoppingListUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingListState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadShoppingList()
    }

    private fun loadShoppingList() {
        viewModelScope.launch {
            useCases.getShoppingList().onEach {
                _state.value = _state.value.copy(
                    shoppingList = it,
                    isLoading = false
                )
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnRemoveFromShoppingList -> removeShoppingListItem(event.ingredient)
            is ShoppingListEvent.OnUndoClick -> undoDeleteIngredient()
        }
    }

    private fun removeShoppingListItem(ingredient: IngredientEntity) {
        viewModelScope.launch {
            useCases.removeShoppingListItem(ingredient.id)

            val updatedList = _state.value.shoppingList.filterNot { it.id == ingredient.id }
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        shoppingList = updatedList,
                        deletedItem = ingredient
                    )
                }
            }
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    message = R.string.item_removed_from_shopping_list,
                    action = R.string.undo
                )
            )
        }
    }

    private fun undoDeleteIngredient() {
        viewModelScope.launch {
            _state.value.deletedItem?.let { deletedItem ->
                useCases.addShoppingListItem(deletedItem.toIngredient(), deletedItem.recipeId)

                withContext(Dispatchers.Main) {
                    _state.update { currentState ->
                        currentState.copy(
                            shoppingList = currentState.shoppingList + deletedItem,
                            deletedItem = null
                        )
                    }
                }
            }
        }
    }

}