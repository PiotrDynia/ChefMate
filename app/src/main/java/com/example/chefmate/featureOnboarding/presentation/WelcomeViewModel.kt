package com.example.chefmate.featureOnboarding.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.domain.util.DietPreferences
import com.example.chefmate.featureOnboarding.domain.usecase.WelcomeUseCases
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: WelcomeUseCases
) : ViewModel() {

    val pages: List<OnBoardingPage> = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth,
        OnBoardingPage.Fifth,
    )

    private val _dietaryPreferences: MutableState<DietPreferences> = mutableStateOf(DietPreferences())
    val dietaryPreferences: State<DietPreferences> = _dietaryPreferences

    fun onEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.OnAddCuisine -> addCuisine(event.name)
            is WelcomeEvent.OnAddDiet -> addDiet(event.name)
            is WelcomeEvent.OnAddIntolerance -> addIntolerance(event.name)
            is WelcomeEvent.OnRemoveCuisine -> removeCuisine(event.name)
            is WelcomeEvent.OnRemoveDiet -> removeDiet(event.name)
            is WelcomeEvent.OnRemoveIntolerance -> removeIntolerance(event.name)
        }
    }

    private fun removeIntolerance(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            intolerances = _dietaryPreferences.value.intolerances - name
        )
    }

    private fun removeDiet(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            diets = _dietaryPreferences.value.diets - name
        )
    }

    private fun removeCuisine(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            cuisines = _dietaryPreferences.value.cuisines - name
        )
    }

    private fun addIntolerance(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            intolerances = _dietaryPreferences.value.intolerances + name
        )
    }

    private fun addDiet(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            diets = _dietaryPreferences.value.diets + name
        )
    }

    private fun addCuisine(name: String) {
        _dietaryPreferences.value = _dietaryPreferences.value.copy(
            cuisines = _dietaryPreferences.value.cuisines + name
        )
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingState(completed = completed)
        }
    }

    fun saveDietPreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveDietPreferences(dietPreferences = _dietaryPreferences.value)
        }
    }

}