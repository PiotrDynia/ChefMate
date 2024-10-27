package com.example.chefmate.featureShoppingList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.featureShoppingList.domain.usecase.ShoppingListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val useCases: ShoppingListUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingListState())
    val state = _state.asStateFlow()

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
}