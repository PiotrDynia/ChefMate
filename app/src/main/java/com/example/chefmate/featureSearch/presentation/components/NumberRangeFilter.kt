package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberRangeFilter(
    title: String,
    sliderValue: ClosedFloatingPointRange<Float>,
    sliderRange: ClosedFloatingPointRange<Float>,
    sliderSteps: Int,
    onSliderChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = Modifier.padding(horizontal = 8.dp),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        RangeSlider(
            value = sliderValue,
            onValueChange = onSliderChange,
            valueRange = sliderRange,
            steps = sliderSteps,
            startThumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.size(16.dp),
                    interactionSource = remember { MutableInteractionSource() }
                )
            },
            endThumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.size(16.dp),
                    interactionSource = remember { MutableInteractionSource() }
                )
            }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = sliderValue.start.toInt().toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(text = " - ",  style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ))
            Text(
                text = sliderValue.endInclusive.toInt().toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}