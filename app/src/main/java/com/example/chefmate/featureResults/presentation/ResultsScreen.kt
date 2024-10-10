package com.example.chefmate.featureResults.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.core.domain.util.userPreferences.FilterSelections
import com.example.chefmate.core.domain.util.userPreferences.SortType

@Composable
fun ResultsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(FilterSelections.getAllFilterSelectionNames()) { item ->
                FilterChip(
                    selected = false,
                    onClick = {  },
                    label = { Text(text = stringResource(item)) },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(FilterChipDefaults.IconSize)
                                .background(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = CircleShape
                                )
                        ) {
                            Text(
                                text = "1",
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                )
            }
        }
    }
}