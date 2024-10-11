package com.example.chefmate.core.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.presentation.util.UiEvent

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: BottomNavigationViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val navItems = viewModel.navItems
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigateTo(event.route)
                else -> Unit
            }
        }
    }

    LaunchedEffect(currentRoute) {
        viewModel.updateSelectedIndex(currentRoute)
        currentRoute?.let {
            val shouldShowBottomBar = it != Screen.Welcome.route
            viewModel.changeBottomBarVisibility(shouldShowBottomBar)
        }
    }

    AnimatedVisibility(
        visible = state.isBottomBarVisible,
        enter = slideInVertically(initialOffsetY = { it })
    ) {
        NavigationBar {
            navItems.forEachIndexed { index, item ->
                val icon =
                    if (state.selectedItemIndex == index) item.selectedIcon else item.unselectedIcon
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(id = item.titleResId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.titleResId),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = state.selectedItemIndex == index,
                    onClick = { viewModel.onItemClick(index = index, navRoute = item.route) }
                )
            }
        }
    }
}