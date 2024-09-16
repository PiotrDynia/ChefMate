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
import com.example.chefmate.core.domain.util.DietPreferences
import com.example.chefmate.core.domain.util.getAllCuisineNames
import com.example.chefmate.core.domain.util.getAllDietNames
import com.example.chefmate.core.domain.util.getAllIntoleranceNames
import com.example.chefmate.core.presentation.util.SelectableDropdownMenu
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage
import com.example.chefmate.featureOnboarding.presentation.WelcomeEvent

@Composable
fun DietaryPreferencesDropdownMenus(
    pagerState: PagerState,
    onEvent: (WelcomeEvent) -> Unit,
    selectedDietaryPreferences: DietPreferences,
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
                selectedValues = selectedDietaryPreferences.cuisines,
                onAdd = { cuisine -> onEvent(WelcomeEvent.OnAddCuisine(cuisine))},
                onRemove = { cuisine -> onEvent(WelcomeEvent.OnRemoveCuisine(cuisine))},
                modifier = Modifier.fillMaxWidth()
            )
            SelectableDropdownMenu(
                placeholder = R.string.select_your_diet,
                values = getAllDietNames(),
                selectedValues = selectedDietaryPreferences.diets,
                onAdd = { diet -> onEvent(WelcomeEvent.OnAddDiet(diet))},
                onRemove = { diet -> onEvent(WelcomeEvent.OnRemoveDiet(diet))},
                modifier = Modifier.fillMaxWidth()
            )
            SelectableDropdownMenu(
                placeholder = R.string.select_your_intolerances,
                values = getAllIntoleranceNames(),
                selectedValues = selectedDietaryPreferences.intolerances,
                onAdd = { intolerance -> onEvent(WelcomeEvent.OnAddIntolerance(intolerance))},
                onRemove = { intolerance -> onEvent(WelcomeEvent.OnRemoveIntolerance(intolerance))},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}