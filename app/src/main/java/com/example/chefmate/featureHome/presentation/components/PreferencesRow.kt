package com.example.chefmate.featureHome.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.Cuisine

@Composable
fun PreferencesRow(modifier: Modifier = Modifier) {
    val selectedCuisines = remember { mutableStateMapOf<Cuisine, Boolean>() }

    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.cuisines),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(Cuisine.entries) { cuisine ->
                val isSelected = selectedCuisines[cuisine] == true

                val cardColor = if (isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.background
                }

                val textColor = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onBackground
                }
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    modifier = Modifier
                        .size(width = 104.dp, height = 88.dp)
                        .clickable {
                            selectedCuisines[cuisine] = !isSelected
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .background(cardColor)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(cuisine.imageResId),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = cuisine.displayName,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium.copy(color = textColor),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(32.dp),
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}