package com.example.chefmate.featureOnboarding.presentation

sealed class WelcomeEvent {
    data class OnAddCuisine(val name: String) : WelcomeEvent()
    data class OnAddIntolerance(val name: String) : WelcomeEvent()
    data class OnAddDiet(val name: String) : WelcomeEvent()
    data class OnRemoveCuisine(val name: String) : WelcomeEvent()
    data class OnRemoveIntolerance(val name: String) : WelcomeEvent()
    data class OnRemoveDiet(val name: String) : WelcomeEvent()
}