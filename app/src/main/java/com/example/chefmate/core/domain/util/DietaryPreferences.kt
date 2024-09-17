package com.example.chefmate.core.domain.util

data class DietPreferences(
    val diets: List<String> = emptyList(),
    val cuisines: List<String> = emptyList(),
    val intolerances: List<String> = emptyList()
)