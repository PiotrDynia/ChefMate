package com.example.chefmate.featureSplash.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.featureSplash.domain.usecase.SplashUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: SplashUseCases
) : ViewModel() {
    private val _startDestination: MutableState<String?> = mutableStateOf(null)
    val startDestination: State<String?> = _startDestination

    init {
        viewModelScope.launch {
            useCases.readOnBoardingState().collect { completed ->
                _startDestination.value = if (completed) {
                    Screen.Home.route
                } else {
                    Screen.Welcome.route
                }
            }
        }
    }
}