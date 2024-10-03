package com.example.chefmate.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.Screen
import javax.inject.Inject

class BottomNavigationViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(BottomNavigationState())
    val state: State<BottomNavigationState> = _state

    // TODO change routes when we have them
    val navItems = listOf(
        BottomNavigationItem(
            titleResId = R.string.home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Screen.Home.route
        ),
        BottomNavigationItem(
            titleResId = R.string.search,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            route = Screen.Search.route
        ),
        BottomNavigationItem(
            titleResId = R.string.saved,
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder,
            route = Screen.Home.route
        ),
        BottomNavigationItem(
            titleResId = R.string.ai_chatbot,
            selectedIcon = Icons.Filled.AutoAwesome,
            unselectedIcon = Icons.Outlined.AutoAwesome,
            route = Screen.Home.route
        )
    )

    fun onItemClick(index: Int) {
        _state.value = _state.value.copy(
            selectedItemIndex = index
        )
    }

    fun changeBottomBarVisibility(value: Boolean) {
        _state.value = _state.value.copy(
            isBottomBarVisible = value
        )
    }
}