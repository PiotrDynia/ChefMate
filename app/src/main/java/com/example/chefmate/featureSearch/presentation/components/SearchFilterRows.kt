package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.Cuisine
import com.example.chefmate.core.domain.util.Diet
import com.example.chefmate.core.domain.util.Intolerance
import com.example.chefmate.core.domain.util.MealType

@Composable
fun SearchFilterRows(modifier: Modifier = Modifier) {
    FilterRow(
        items = Cuisine.entries,
        title = stringResource(R.string.cuisines),
        getDisplayName = { it.displayName },
        selectedItems = emptySet(),
        onItemSelected = {}
    )
    FilterRow(
        items = Cuisine.entries,
        title = stringResource(R.string.exclude_cuisines),
        getDisplayName = { it.displayName },
        selectedItems = emptySet(),
        onItemSelected = {}
    )
    FilterRow(
        items = Diet.entries,
        title = stringResource(R.string.diets),
        getDisplayName = { it.displayName },
        selectedItems = emptySet(),
        onItemSelected = {}
    )
    FilterRow(
        items = Intolerance.entries,
        title = stringResource(R.string.intolerances),
        getDisplayName = { it.displayName },
        selectedItems = emptySet(),
        onItemSelected = {}
    )
    FilterRow(
        items = MealType.entries,
        title = stringResource(R.string.meal_types),
        getDisplayName = { it.displayName },
        selectedItems = emptySet(),
        onItemSelected = {}
    )

}