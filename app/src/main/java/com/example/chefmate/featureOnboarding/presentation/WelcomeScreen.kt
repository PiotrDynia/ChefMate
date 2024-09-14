package com.example.chefmate.featureOnboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chefmate.R

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome_animation))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}