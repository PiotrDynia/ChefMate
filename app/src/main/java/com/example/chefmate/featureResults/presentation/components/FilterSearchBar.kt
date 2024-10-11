package com.example.chefmate.featureResults.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.FilterChipWithCount
import com.example.chefmate.core.presentation.util.FilterChipWithRange
import com.example.chefmate.core.presentation.util.FilterChipWithValue
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import com.example.chefmate.featureSearch.presentation.components.SearchBar

@Composable
fun FilterSearchBar(
    filterSelection: SearchFilterSelection,
    filterSets: List<Pair<Int, Int>>,
    modifier: Modifier = Modifier
) {
    SearchBar(value = filterSelection.query, onEvent = {})
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        filterSets.forEach { (label, count) ->
            item {
                FilterChipWithCount(
                    label = stringResource(label),
                    count = count
                )
            }
        }
        item {
            FilterChipWithRange(
                label = stringResource(R.string.servings_lowercase),
                range = "${filterSelection.minServings}-${filterSelection.maxServings}"
            )
        }

        item {
            FilterChipWithRange(
                label = stringResource(R.string.calories),
                range = "${filterSelection.minCalories}-${filterSelection.maxCalories}"
            )
        }

        item {
            FilterChipWithValue(
                label = stringResource(R.string.sort_lowercase),
                value = filterSelection.sort
            )
        }
    }
}