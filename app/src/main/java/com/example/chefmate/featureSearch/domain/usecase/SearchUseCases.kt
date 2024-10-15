package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.domain.usecase.ReadDietPreferences

data class SearchUseCases(
    val searchRecipes: SearchRecipes,
    val readDietPreferences: ReadDietPreferences
)