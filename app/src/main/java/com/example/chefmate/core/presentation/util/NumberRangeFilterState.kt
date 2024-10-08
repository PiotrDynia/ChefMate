package com.example.chefmate.core.presentation.util

data class NumberRangeFilterState(
    val sliderValue: ClosedFloatingPointRange<Float>,
    val sliderValueRange: ClosedFloatingPointRange<Float>,
    val minTextFieldValue: String,
    val maxTextFieldValue: String,
    val onSliderChange: (ClosedFloatingPointRange<Float>) -> Unit,
    val onMinTextFieldChange: (String) -> Unit,
    val onMaxTextFieldChange: (String) -> Unit
)