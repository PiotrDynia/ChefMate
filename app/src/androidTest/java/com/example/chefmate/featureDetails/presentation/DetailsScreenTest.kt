package com.example.chefmate.featureDetails.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
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
class DetailsScreenTest {

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
                    startDestination = Screen.Details.route + "?id=716429"
                )
            }
        }
        composeRule.waitUntilAtLeastOneExists(
            hasContentDescription(context.getString(R.string.back)),
            timeoutMillis = 3000
        )
    }

    @Test
    fun checkScreenIsScrollable() {
        composeRule
            .onNodeWithText(context.getString(R.string.summary))
            .performTouchInput {
                swipeUp()
            }
    }

    @Test
    fun checkBackButtonIsClickable() {
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.back))
            .assertHasClickAction()
    }

    @Test
    fun checkBookmarkButtonIsClickable() {
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.bookmark))
            .assertHasClickAction()


        composeRule.onNodeWithText(context.getString(R.string.instructions)).performScrollTo()
    }

    @Test
    fun checkAddAllTextIsClickable() {
        composeRule.onNodeWithText(context.getString(R.string.ingredients)).performScrollTo()

        composeRule
            .onNodeWithText(context.getString(R.string.add_all))
            .assertHasClickAction()
    }

    @Test
    fun checkPlusButtonIsClickable() {
        composeRule.onNodeWithText(context.getString(R.string.ingredients)).performScrollTo()

        composeRule
            .onAllNodesWithContentDescription(context.getString(R.string.add_to_shopping_list))
            .onFirst()
            .assertHasClickAction()
    }
}