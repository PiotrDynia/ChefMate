package com.example.chefmate.featureBookmarks.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chefmate.MainActivity
import com.example.chefmate.R
import com.example.chefmate.core.presentation.navigation.SetupNavGraph
import com.example.chefmate.core.presentation.util.Screen
import com.example.chefmate.di.CoreModule
import com.example.chefmate.ui.theme.ChefMateTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoreModule::class)
class BookmarksScreenTest {

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
                    startDestination = Screen.Bookmarks.route
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
            .onNodeWithText(context.getString(R.string.search))
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
    fun checkSearchButtonIsClickable() {
        composeRule
            .onNodeWithText(context.getString(R.string.search))
            .assertHasClickAction()
    }
}