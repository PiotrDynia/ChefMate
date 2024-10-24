package com.example.chefmate.featureBookmarks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureBookmarks.domain.usecase.BookmarksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val useCases: BookmarksUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarksState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    init {
        loadBookmarkedRecipes()
    }

    private fun loadBookmarkedRecipes() {
        useCases.getBookmarkedRecipes().onEach {
            _state.value = _state.value.copy(
                bookmarkedRecipes = it,
                isLoading = false
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: BookmarksEvent) {
        when(event) {
            is BookmarksEvent.OnRecipeClick -> navigateToDetails(event.id)
            is BookmarksEvent.OnSearchClick -> filterRecipes()
            is BookmarksEvent.OnSearchInputChange -> updateSearchInput(event.input)
        }
    }

    private fun navigateToDetails(id: Int) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Screen.Details.route + "?id=${id}&isBookmarked=true"))
        }
    }

    private fun filterRecipes() {
        _state.update {
            it.copy(
                bookmarkedRecipes = it.bookmarkedRecipes.filter { recipe ->
                    recipe.title.contains(state.value.searchInput, ignoreCase = true)
                }
            )
        }
    }

    private fun updateSearchInput(input: String) {
        _state.update {
            it.copy(searchInput = input)
        }
    }
}