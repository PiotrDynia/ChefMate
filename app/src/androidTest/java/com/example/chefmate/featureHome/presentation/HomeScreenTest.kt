package com.example.chefmate.featureHome.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chefmate.MainActivity
import com.example.chefmate.R
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @OptIn(ExperimentalTestApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val navController = rememberNavController()
            ChefMateTheme {
                SetupNavGraph(
                    navController = navController,
                    snackbarHostState = remember { SnackbarHostState() },
                    startDestination = Screen.Home.route
                )
            }
        }
        composeRule.waitUntilAtLeastOneExists(
            hasText(context.getString(R.string.what_would_you_like_to_cook_today)),
            timeoutMillis = 3000
        )
    }

    @Test
    fun checkEachSectionIsDisplayed() {
        composeRule
            .onNodeWithText(context.getString(R.string.what_would_you_like_to_cook_today))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.search_for_recipes))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.cuisines))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.diets))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.intolerances))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.meal_types))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.recommendations))
            .assertIsDisplayed()
    }

    @Test
    fun checkSearchButtonsAreClickable() {
        composeRule
            .onNodeWithText(context.getString(R.string.search))
            .assertHasClickAction()
        composeRule
            .onNodeWithText(context.getString(R.string.advanced_search))
            .assertHasClickAction()
    }

    @Test
    fun checkSearchBarIsEditable() {
        composeRule
            .onNodeWithText(context.getString(R.string.search_for_recipes))
            .performTextInput("Hello")
        composeRule
            .onNodeWithText("Hello")
            .assertIsDisplayed()
    }

    @Test
    fun checkAccountIconIsClickable() {
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.go_to_profile))
            .assertHasClickAction()
    }

    @Test
    fun checkSeeAllIsClickable() {
        composeRule
            .onNodeWithText(context.getString(R.string.see_all))
            .assertHasClickAction()
    }

    @Test
    fun checkPreferencesAreClickable() {
        composeRule
            .onNodeWithText("African")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("Ketogenic")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("Dairy")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("Main course")
            .assertHasClickAction()
    }
}