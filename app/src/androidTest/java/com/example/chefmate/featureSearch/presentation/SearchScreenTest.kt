package com.example.chefmate.featureSearch.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chefmate.MainActivity
import com.example.chefmate.R
import com.example.chefmate.core.presentation.navigation.BottomNavigationViewModel
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchScreenTest {

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
                SetupNavGraph(
                    navController = navController,
                    snackbarHostState = remember { SnackbarHostState() },
                    startDestination = Screen.Search.route
                )
            }
        }
    }

    @Test
    fun checkEachSectionIsDisplayed() {
        composeRule
            .onNodeWithText(context.getString(R.string.search_for_recipes))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.cuisines))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.exclude_cuisines))
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
            .onNodeWithText(context.getString(R.string.calories_per_serving))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.servings))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.sort))
            .assertIsDisplayed()
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.search_filtered_recipes))
            .assertIsDisplayed()
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
    fun checkSliderIsDraggable() {
        composeRule
            .onAllNodesWithContentDescription("Range start")
            .onFirst()
            .performTouchInput {
                swipeRight()
            }

        composeRule
            .onAllNodesWithContentDescription("Range end")
            .onFirst()
            .performTouchInput {
                swipeLeft()
            }
    }

    @Test
    fun checkSearchButtonIsClickable() {
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.search_filtered_recipes))
            .assertHasClickAction()
    }

    @Test
    fun checkPreferencesAreClickable() {
        composeRule
            .onNodeWithText("Ketogenic")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("Dairy")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("Main course")
            .assertHasClickAction()
        composeRule
            .onNodeWithText("popularity")
            .assertHasClickAction()
    }

}