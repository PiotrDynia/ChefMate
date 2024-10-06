package com.example.chefmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            ChefMateTheme {
                val navController = rememberNavController()
                val splashViewModel: SplashViewModel = hiltViewModel()
                val bottomNavigationViewModel: BottomNavigationViewModel = hiltViewModel()

                Scaffold(
                    containerColor = Color(0xFFEAEAEA),
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            viewModel = bottomNavigationViewModel
                        )
                    }
                ) { padding ->
                    val screen by splashViewModel.startDestination
                    screen?.let {
                        SetupNavGraph(
                            navController = navController,
                            startDestination = it,
                            modifier = Modifier.padding(padding),
                            bottomNavigationViewModel = bottomNavigationViewModel
                        )
                    }
                }
            }
        }
    }
}