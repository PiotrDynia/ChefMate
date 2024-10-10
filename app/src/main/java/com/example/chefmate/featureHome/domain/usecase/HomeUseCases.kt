package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.domain.usecase.ReadDietPreferences
import com.example.chefmate.core.domain.usecase.SaveDietPreferences

data class HomeUseCases(
    val readDietPreferences: ReadDietPreferences,
    val saveDietPreferences: SaveDietPreferences,
    val fetchRandomRecipes: FetchRandomRecipes,
    val fetchRecipes: FetchRecipes,
    val getAutocompleteRecipes: GetAutocompleteRecipes
)