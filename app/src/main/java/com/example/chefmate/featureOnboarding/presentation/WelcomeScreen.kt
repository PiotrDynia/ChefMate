package com.example.chefmate.featureOnboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chefmate.core.domain.util.navigateTo
import com.example.chefmate.core.presentation.util.UiEvent
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage
import com.example.chefmate.featureOnboarding.presentation.components.DietaryPreferencesScreen
import com.example.chefmate.featureOnboarding.presentation.components.OnBoardingButton
import com.example.chefmate.featureOnboarding.presentation.components.PagerScreen
import com.google.accompanist.pager.HorizontalPagerIndicator

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { OnBoardingPage.PAGES_COUNT })

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigateTo(event.route)
                UiEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = viewModel.pages[position])
        }
        if (pagerState.currentPage == OnBoardingPage.LAST_SCREEN_INDEX) {
            DietaryPreferencesScreen(
                pagerState = pagerState,
                onEvent = viewModel::onEvent,
                selectedDietaryPreferences = viewModel.dietaryPreferences.value,
            )
        } else {
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                pageCount = OnBoardingPage.PAGES_COUNT,
                pagerState = pagerState
            )
        }
        OnBoardingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            pagerState = pagerState,
            onClick = { viewModel.onEvent(WelcomeEvent.OnButtonClick) }
        )
    }
}