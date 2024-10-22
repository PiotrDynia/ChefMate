package com.example.chefmate.featureBookmarks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureBookmarks.presentation.BookmarksEvent
import com.example.chefmate.featureSearch.presentation.components.SearchBar

@Composable
fun SearchBookmarksBar(input: String, onEvent: (BookmarksEvent) -> Unit, modifier: Modifier = Modifier) {
    SearchBar(
        value = input,
        onTextChange = { onEvent(BookmarksEvent.OnSearchInputChange(it)) }
    )
    Button(
        onClick = { onEvent(BookmarksEvent.OnSearchClick) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = stringResource(R.string.search))
    }
}