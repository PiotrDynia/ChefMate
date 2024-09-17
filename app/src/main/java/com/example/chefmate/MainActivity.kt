package com.example.chefmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.featureSplash.presentation.SplashViewModel
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        enableEdgeToEdge()
        setContent {
            ChefMateTheme {
                val navController = rememberNavController()
                val screen by splashViewModel.startDestination
                screen?.let { SetupNavGraph(navController = navController, startDestination = it) }
            }
        }
    }
}