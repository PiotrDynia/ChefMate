package com.example.chefmate.featureOnboarding.domain.usecase

import com.example.chefmate.core.domain.usecase.SaveDietPreferences

data class WelcomeUseCases(
    val saveOnBoardingState: SaveOnBoardingState,
    val saveDietPreferences: SaveDietPreferences
)