package com.example.chefmate.featureOnboarding.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chefmate.MainActivity
import com.example.chefmate.R
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.di.CoreModule
import com.example.chefmate.featureSplash.presentation.SplashViewModel
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoreModule::class)
class WelcomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val navController = rememberNavController()
            ChefMateTheme {
                val splashViewModel: SplashViewModel = hiltViewModel()
                val screen by splashViewModel.startDestination
                screen?.let { SetupNavGraph(
                    navController = navController,
                    snackbarHostState = remember { SnackbarHostState() },
                    startDestination = it
                ) }
            }
        }
    }

    @Test
    fun checkEachScreenIsDisplayed() {
        composeRule
            .onNodeWithText(context.getString(R.string.chefmate))
            .assertIsDisplayed()
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.browse_recipes))
            .assertIsDisplayed()
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.shopping_list))
            .assertIsDisplayed()
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.virtual_assistant))
            .assertIsDisplayed()
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.dietary_preferences))
            .assertIsDisplayed()
    }

    @Test
    fun checkSkipButtonIsDisplayedOnFirstScreens() {
        composeRule
            .onNodeWithText(context.getString(R.string.skip))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.chefmate))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.skip))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.browse_recipes))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.skip))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.shopping_list))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.skip))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.virtual_assistant))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.skip))
            .assertIsNotDisplayed()
    }

    @Test
    fun checkFinishButtonIsDisplayedOnLastScreen() {
        composeRule
            .onNodeWithText(context.getString(R.string.finish))
            .assertIsNotDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.chefmate))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.finish))
            .assertIsNotDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.browse_recipes))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.finish))
            .assertIsNotDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.shopping_list))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.finish))
            .assertIsNotDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.virtual_assistant))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.finish))
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun checkDietaryPreferencesInputsAreDisplayedOnLastScreen() {
        composeRule
            .onNodeWithText(context.getString(R.string.chefmate))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.browse_recipes))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.shopping_list))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.virtual_assistant))
            .performTouchInput {
                swipeLeft()
            }
        composeRule
            .onNodeWithText(context.getString(R.string.select_your_favourite_cuisines))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.select_your_diet))
            .assertIsDisplayed()
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.select_your_intolerances))
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}