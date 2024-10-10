package com.example.chefmate.featureHome.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureHome.presentation.HomeEvent
import com.example.chefmate.featureHome.presentation.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onAdvancedSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = state.isSearchAutocompleteExpanded,
            onExpandedChange = { }
        ) {
            TextField(
                value = state.searchInput,
                onValueChange = { onEvent(HomeEvent.OnSearchInputChange(it)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background
                ),
                placeholder = {
                    Text(stringResource(R.string.search_for_recipes))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .menuAnchor(type = MenuAnchorType.PrimaryEditable)
            )
            ExposedDropdownMenu(
                expanded = state.isSearchAutocompleteExpanded,
                onDismissRequest = { onEvent(HomeEvent.OnDismissAutocomplete) }
            ) {
                if (state.autocompleteErrorMessageResId != null) {
                    DropdownMenuItem(
                        text = { Text(
                            text = stringResource(id = state.autocompleteErrorMessageResId),
                            fontStyle = FontStyle.Italic
                        ) },
                        onClick = { },
                        enabled = false
                    )
                } else {
                    state.autocompletedResults.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.title) },
                            onClick = {
                                onEvent(HomeEvent.OnAutocompleteItemClick)
                            }
                        )
                    }
                }
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.search))
        }
        Button(onClick = onAdvancedSearchClick) {
            Text(text = stringResource(R.string.advanced_search))
        }
    }
}