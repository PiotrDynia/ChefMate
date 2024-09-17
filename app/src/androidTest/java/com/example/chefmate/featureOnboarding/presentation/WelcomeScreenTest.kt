package com.example.chefmate.featureOnboarding.presentation

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chefmate.MainActivity
import com.example.chefmate.R
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.featureSplash.presentation.SplashViewModel
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class WelcomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Inject
    lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val navController = rememberNavController()
            ChefMateTheme {
                val screen by splashViewModel.startDestination
                screen?.let { SetupNavGraph(navController = navController, startDestination = it) }
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