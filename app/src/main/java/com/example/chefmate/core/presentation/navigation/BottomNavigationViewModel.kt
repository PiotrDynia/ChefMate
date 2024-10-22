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
import androidx.lifecycle.viewModelScope
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(BottomNavigationState())
    val state: State<BottomNavigationState> = _state

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

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
            route = Screen.Bookmarks.route
        ),
        BottomNavigationItem(
            titleResId = R.string.ai_chatbot,
            selectedIcon = Icons.Filled.AutoAwesome,
            unselectedIcon = Icons.Outlined.AutoAwesome,
            route = ""
        )
    )

    private val routeToIndex = navItems.mapIndexed { index, item -> item.route to index }.toMap()

    fun onItemClick(index: Int, navRoute: String) {
        _state.value = _state.value.copy(
            selectedItemIndex = index
        )
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(navRoute))
        }
    }

    fun changeBottomBarVisibility(value: Boolean) {
        _state.value = _state.value.copy(
            isBottomBarVisible = value
        )
    }

    fun updateSelectedIndex(route: String?) {
        route?.let {
            if (routeToIndex.containsKey(it)) {
                _state.value = _state.value.copy(selectedItemIndex = routeToIndex[it] ?: 0)
            } else {
                _state.value = _state.value.copy(selectedItemIndex = -1)
            }
        }
    }
}