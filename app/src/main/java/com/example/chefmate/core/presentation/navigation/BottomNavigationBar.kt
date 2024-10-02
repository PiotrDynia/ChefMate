package com.example.chefmate.core.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chefmate.core.presentation.util.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    isSplashScreenLoading: Boolean,
    viewModel: BottomNavigationViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.value
    val navItems = viewModel.navItems
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry?.destination?.route) {
        if (!isSplashScreenLoading) {
            val shouldShowBottomBar = navBackStackEntry?.destination?.route != Screen.Welcome.route
            viewModel.changeBottomBarVisibility(shouldShowBottomBar)
        }
    }

    AnimatedVisibility(state.isBottomBarVisible) {
        NavigationBar {
            navItems.forEachIndexed { index, item ->
                val icon = if (state.selectedItemIndex == index) item.selectedIcon else item.unselectedIcon
                NavigationBarItem(
                    icon = { Icon(imageVector = icon, contentDescription = stringResource(id = item.titleResId)) },
                    label = { Text(
                        text = stringResource(id = item.titleResId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) },
                    selected = state.selectedItemIndex == index,
                    onClick = {
                        viewModel.onItemClick(index)
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}