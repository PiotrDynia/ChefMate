package com.example.chefmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.chefmate.core.presentation.navigation.BottomNavigationBar
import com.example.chefmate.core.presentation.navigation.BottomNavigationViewModel
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.featureSplash.presentation.SplashViewModel
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel
    @Inject
    lateinit var bottomNavigationViewModel: BottomNavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            ChefMateTheme {
                val navController = rememberNavController()

                Scaffold(
                    containerColor = Color(0xFFEAEAEA),
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            viewModel = bottomNavigationViewModel
                        )
                    }
                ) { _ ->
                    val screen by splashViewModel.startDestination
                    screen?.let {
                        SetupNavGraph(
                            navController = navController,
                            startDestination = it
                        )
                    }
                }
            }
        }
    }
}