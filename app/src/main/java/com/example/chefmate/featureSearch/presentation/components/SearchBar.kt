package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureSearch.presentation.SearchEvent
import com.example.chefmate.featureSearch.presentation.SearchState

@Composable
fun SearchBar(
    value: String,
    onEvent: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = value,
            onValueChange = { onEvent(SearchEvent.OnSearchInputChange(it)) },
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
        )
    }
}