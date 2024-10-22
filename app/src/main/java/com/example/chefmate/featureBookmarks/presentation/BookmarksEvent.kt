package com.example.chefmate.featureBookmarks.presentation

sealed class BookmarksEvent {
    data class OnSearchInputChange(val input: String): BookmarksEvent()
    data class OnRecipeClick(val id: Int): BookmarksEvent()
    data object OnSearchClick: BookmarksEvent()
}