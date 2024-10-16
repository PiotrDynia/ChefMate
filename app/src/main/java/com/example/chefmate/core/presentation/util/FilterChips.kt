package com.example.chefmate.core.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FilterChipWithCount(label: String, count: Int, onClick: () -> Unit) {
    FilterChip(
        selected = false,
        onClick = onClick,
        label = { Text(text = label) },
        leadingIcon = if (count > 0) {
            {
                Box(
                    modifier = Modifier
                        .size(FilterChipDefaults.IconSize)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = count.toString(),
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        } else {
            null
        }
    )
}

@Composable
fun FilterChipWithoutCount(label: String, value: String, onClick: () -> Unit) {
    FilterChip(
        selected = false,
        onClick = onClick,
        label = {
            Text(text = "$label: ")
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
            )
        }
    )
}