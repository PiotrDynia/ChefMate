package com.example.chefmate.featureBookmarks.presentation

import androidx.lifecycle.ViewModel
import com.example.chefmate.featureBookmarks.BookmarksState
import com.example.chefmate.featureBookmarks.domain.usecase.BookmarksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val useCases: BookmarksUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarksState())
    val state = _state.asStateFlow()

    init {
        loadBookmarkedRecipes()
    }

    private fun loadBookmarkedRecipes() {
        useCases.getBookmarkedRecipes().onEach {
            _state.value = _state.value.copy(
                bookmarkedRecipes = it
            )
        }
    }
}