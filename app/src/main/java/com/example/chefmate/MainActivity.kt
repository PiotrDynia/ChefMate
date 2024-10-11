package com.example.chefmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            ChefMateTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val splashViewModel: SplashViewModel = hiltViewModel()

                Scaffold(
                    containerColor = Color(0xFFEAEAEA),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { padding ->
                    val screen by splashViewModel.startDestination
                    screen?.let {
                        SetupNavGraph(
                            snackbarHostState = snackbarHostState,
                            navController = navController,
                            startDestination = it,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}