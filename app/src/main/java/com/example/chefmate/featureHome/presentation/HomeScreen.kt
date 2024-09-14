package com.example.chefmate.featureHome.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chefmate.featureOnboarding.presentation.WelcomeViewModel

@Composable
fun HomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "HOME",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )
        Button(onClick = { viewModel.saveOnBoardingState(false) }) {

        }
    }
}