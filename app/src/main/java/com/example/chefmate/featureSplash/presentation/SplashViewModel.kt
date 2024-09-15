package com.example.chefmate.featureSplash.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.core.data.repository.DataStoreRepository
import com.example.chefmate.featureSplash.domain.usecase.SplashUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCases: SplashUseCases
) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String?> = mutableStateOf(null)
    val startDestination: State<String?> = _startDestination

    init {
        viewModelScope.launch {
            useCases.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.Home.route
                } else {
                    _startDestination.value = Screen.Welcome.route
                }
            }
            _isLoading.value = false
        }
    }
}