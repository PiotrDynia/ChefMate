package com.example.chefmate.featureShoppingList.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureDetails.domain.model.IngredientEntity
import com.example.chefmate.featureShoppingList.presentation.ShoppingListEvent

@Composable
fun ShoppingListItems(items: List<IngredientEntity>, onRemoveItem: (IngredientEntity) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(items) { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.name, modifier = Modifier.weight(1f))

                IconButton(onClick = { onRemoveItem(item) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.remove_item),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}