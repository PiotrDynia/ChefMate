package com.example.chefmate.featureOnboarding.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.getAllCuisineNames
import com.example.chefmate.core.domain.util.getAllDietNames
import com.example.chefmate.core.domain.util.getAllIntoleranceNames
import com.example.chefmate.core.presentation.util.SelectableDropdownMenu
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage

@Composable
fun DietaryPreferencesDropdownMenus(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(bottom = 16.dp),
        visible = pagerState.currentPage == OnBoardingPage.LAST_SCREEN_INDEX
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            SelectableDropdownMenu(
                placeholder = R.string.select_your_favourite_cuisines,
                values = getAllCuisineNames(),
                modifier = Modifier.fillMaxWidth()
            )
            SelectableDropdownMenu(
                placeholder = R.string.select_your_diet,
                values = getAllDietNames(),
                modifier = Modifier.fillMaxWidth()
            )
            SelectableDropdownMenu(
                placeholder = R.string.select_your_intolerances,
                values = getAllIntoleranceNames(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}