package com.example.chefmate.featureResults.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.core.presentation.util.FilterChipWithCount
import com.example.chefmate.core.presentation.util.FilterChipWithRange
import com.example.chefmate.core.presentation.util.FilterChipWithValue
import com.example.chefmate.featureSearch.presentation.SearchEvent
import com.example.chefmate.featureSearch.presentation.SearchState
import com.example.chefmate.featureSearch.presentation.components.SearchBar

@Composable
fun FilterSearchBar(
    searchState: SearchState,
    filterSets: List<Pair<Int, Int>>,
    onSearchFilterEvent: (SearchEvent) -> Unit,
    onFilterChipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        value = searchState.searchInput,
        onTextChange = { onSearchFilterEvent(SearchEvent.OnSearchInputChange(it)) }
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        filterSets.forEach { (label, count) ->
            item {
                FilterChipWithCount(
                    label = stringResource(label),
                    count = count,
                    onClick = onFilterChipClick
                )
            }
        }
        item {
            FilterChipWithRange(
                label = stringResource(R.string.servings_lowercase),
                range = "${searchState.servingsSliderPosition.start.toInt()}-${searchState.servingsSliderPosition.endInclusive.toInt()}",
                onClick = onFilterChipClick
            )
        }

        item {
            FilterChipWithRange(
                label = stringResource(R.string.calories),
                range = "${searchState.caloriesSliderPosition.start.toInt()}-${searchState.caloriesSliderPosition.endInclusive.toInt()}",
                onClick = onFilterChipClick
            )
        }

        item {
            FilterChipWithValue(
                label = stringResource(R.string.sort_lowercase),
                value = searchState.selectedSortType,
                onClick = onFilterChipClick
            )
        }
    }
    Button(
        onClick = { onSearchFilterEvent(SearchEvent.OnSearchClick) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = stringResource(R.string.search))
    }
}