package com.example.chefmate.featureOnboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.data.repository.DataStoreRepository
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

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingState(completed = completed)
        }
    }

}