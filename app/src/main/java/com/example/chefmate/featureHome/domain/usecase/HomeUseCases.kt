package com.example.chefmate.featureHome.domain.usecase

data class HomeUseCases(
    val readDietPreferences: ReadDietPreferences,
    val fetchRandomRecipes: FetchRandomRecipes,
    val fetchRecipes: FetchRecipes
)