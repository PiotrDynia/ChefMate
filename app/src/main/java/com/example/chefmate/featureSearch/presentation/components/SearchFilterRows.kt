package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.getAllCuisineNames
import com.example.chefmate.core.domain.util.getAllDietNames
import com.example.chefmate.core.domain.util.getAllIntoleranceNames
import com.example.chefmate.core.domain.util.getAllMealTypeNames
import com.example.chefmate.core.presentation.util.NumberRangeFilterState
import com.example.chefmate.featureSearch.presentation.SearchEvent
import com.example.chefmate.featureSearch.presentation.SearchState

@Composable
fun SearchFilterRows(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier) {
    FilterRow(
        items = getAllCuisineNames(),
        title = stringResource(R.string.cuisines),
        selectedItems = state.selectedCuisines,
        onItemSelected = { onEvent(SearchEvent.OnCuisineSelected(it))}
    )
    FilterRow(
        items = getAllCuisineNames(),
        title = stringResource(R.string.exclude_cuisines),
        selectedItems = state.excludedCuisines,
        onItemSelected = { onEvent(SearchEvent.OnExcludeCuisineSelected(it)) }
    )
    FilterRow(
        items = getAllDietNames(),
        title = stringResource(R.string.diets),
        selectedItems = state.selectedDiets,
        onItemSelected = { onEvent(SearchEvent.OnDietSelected(it)) }
    )
    FilterRow(
        items = getAllIntoleranceNames(),
        title = stringResource(R.string.intolerances),
        selectedItems = state.selectedIntolerances,
        onItemSelected = { onEvent(SearchEvent.OnIntoleranceSelected(it)) }
    )
    FilterRow(
        items = getAllMealTypeNames(),
        title = stringResource(R.string.meal_types),
        selectedItems = state.selectedMealTypes,
        onItemSelected = { onEvent(SearchEvent.OnMealTypeSelected(it)) }
    )
    NumberRangeFilter(
        title = stringResource(R.string.calories),
        numberRangeFilterState = NumberRangeFilterState(
            sliderValue = state.caloriesSliderPosition,
            sliderValueRange = state.caloriesSliderRange,
            minTextFieldValue = state.caloriesMin.toString(),
            maxTextFieldValue = state.caloriesMax.toString(),
            onSliderChange = { onEvent(SearchEvent.OnCaloriesSliderPositionChange(it)) },
            onMinTextFieldChange = { onEvent(SearchEvent.OnMinCaloriesTextChange(it.toInt())) },
            onMaxTextFieldChange = { onEvent(SearchEvent.OnMaxCaloriesTextChange(it.toInt())) }
        )
    )
//    NumberRangeFilter(
//        title = stringResource(R.string.servings),
//        sliderPosition = state.caloriesSliderPosition,
//        onEvent = onEvent
//    )
    SortRow(
        title = stringResource(R.string.sort),
        onSortSelected = { onEvent(SearchEvent.OnSortTypeSelected(it))},
        selectedSortType = state.selectedSortType
    )
}