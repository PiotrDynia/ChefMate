package com.example.chefmate.featureSearch.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberRangeFilter(title: String, modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableStateOf(0f..10000f) }

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
            value = sliderPosition,
            onValueChange = { range -> sliderPosition = range },
            valueRange = 0f..10000f,
            onValueChangeFinished = { },
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
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = sliderPosition.start.toInt().toString(),
                onValueChange = {},
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .height(52.dp)
                    .width(100.dp)
                    .padding(horizontal = 8.dp)
            )
            Text(text = "-", style = MaterialTheme.typography.headlineLarge)
            TextField(
                value = sliderPosition.endInclusive.toInt().toString(),
                onValueChange = {},
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .height(52.dp)
                    .width(100.dp)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}