package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.userPreferences.Cuisine
import com.example.chefmate.core.domain.util.userPreferences.Diet
import com.example.chefmate.core.domain.util.userPreferences.Intolerance
import com.example.chefmate.core.domain.util.userPreferences.MealType
import com.example.chefmate.featureSearch.presentation.SearchEvent
import com.example.chefmate.featureSearch.presentation.SearchState

@Composable
fun SearchFilterRows(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier) {
    FilterRow(
        items = Cuisine.getAllCuisineNames(),
        title = stringResource(R.string.cuisines),
        selectedItems = state.selectedCuisines,
        onItemSelected = { onEvent(SearchEvent.OnCuisineSelected(it))}
    )
    FilterRow(
        items = Cuisine.getAllCuisineNames(),
        title = stringResource(R.string.exclude_cuisines),
        selectedItems = state.excludedCuisines,
        onItemSelected = { onEvent(SearchEvent.OnExcludeCuisineSelected(it)) }
    )
    FilterRow(
        items = Diet.getAllDietNames(),
        title = stringResource(R.string.diets),
        selectedItems = state.selectedDiets,
        onItemSelected = { onEvent(SearchEvent.OnDietSelected(it)) }
    )
    FilterRow(
        items = Intolerance.getAllIntoleranceNames(),
        title = stringResource(R.string.intolerances),
        selectedItems = state.selectedIntolerances,
        onItemSelected = { onEvent(SearchEvent.OnIntoleranceSelected(it)) }
    )
    FilterRow(
        items = MealType.getAllMealTypeNames(),
        title = stringResource(R.string.meal_types),
        selectedItems = state.selectedMealTypes,
        onItemSelected = { onEvent(SearchEvent.OnMealTypeSelected(it)) }
    )
    NumberRangeFilter(
        title = stringResource(R.string.calories_per_serving),
        sliderValue = state.caloriesSliderPosition,
        sliderRange = state.caloriesSliderRange,
        sliderSteps = 0,
        onSliderChange = { onEvent(SearchEvent.OnCaloriesSliderPositionChange(it)) }
    )
    NumberRangeFilter(
        title = stringResource(R.string.servings),
        sliderValue = state.servingsSliderPosition,
        sliderRange = state.servingsSliderRange,
        sliderSteps = (state.servingsSliderRange.endInclusive - 2).toInt(),
        onSliderChange = { onEvent(SearchEvent.OnServingsSliderPositionChange(it)) }
    )
    SortRow(
        title = stringResource(R.string.sort),
        onSortSelected = { onEvent(SearchEvent.OnSortTypeSelected(it))},
        selectedSortType = state.selectedSortType
    )
}