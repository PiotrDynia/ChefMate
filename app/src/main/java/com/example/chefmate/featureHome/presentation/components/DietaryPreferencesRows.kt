package com.example.chefmate.featureHome.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.MealType
import com.example.chefmate.featureHome.presentation.HomeEvent
import com.example.chefmate.featureHome.presentation.HomeState

@Composable
fun DietaryPreferencesRows(state: HomeState, onEvent: (HomeEvent) -> Unit, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.your_preferences),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        )
    )
    PreferencesRow(
        items = Cuisine.entries,
        title = stringResource(R.string.cuisines),
        getDisplayName = { it.displayName },
        getImageResId = { it.imageResId },
        selectedItems = state.selectedCuisines,
        onItemSelected = { cuisine -> onEvent(HomeEvent.OnCuisineSelected(cuisine)) }
    )
    PreferencesRow(
        items = Diet.entries,
        title = stringResource(R.string.diets),
        getDisplayName = { it.displayName },
        getImageResId = { it.imageResId },
        selectedItems = state.selectedDiets,
        onItemSelected = { diet -> onEvent(HomeEvent.OnDietSelected(diet)) }
    )
    PreferencesRow(
        items = Intolerance.entries,
        title = stringResource(R.string.intolerances),
        getDisplayName = { it.displayName },
        getImageResId = { it.imageResId },
        selectedItems = state.selectedIntolerances,
        onItemSelected = { intolerance -> onEvent(HomeEvent.OnIntoleranceSelected(intolerance)) }
    )
    PreferencesRow(
        items = MealType.entries,
        title = stringResource(R.string.meal_types),
        getDisplayName = { it.displayName },
        getImageResId = { it.imageResId },
        selectedItems = state.selectedMealTypes,
        onItemSelected = { mealType -> onEvent(HomeEvent.OnMealTypeSelected(mealType)) }
    )
}