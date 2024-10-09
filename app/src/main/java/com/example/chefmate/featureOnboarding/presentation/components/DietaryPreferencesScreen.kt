package com.example.chefmate.featureOnboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chefmate.core.domain.util.DietPreferences
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage
import com.example.chefmate.featureOnboarding.presentation.WelcomeEvent
import com.google.accompanist.pager.HorizontalPagerIndicator

@Composable
fun DietaryPreferencesScreen(
    pagerState: PagerState,
    selectedDietaryPreferences: DietPreferences,
    onEvent: (WelcomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DietaryPreferencesDropdownMenus(
            pagerState = pagerState,
            onEvent = onEvent,
            selectedDietaryPreferences = selectedDietaryPreferences,
            modifier = Modifier.fillMaxWidth()
        )
    }
}