package com.example.chefmate.featureChatbot.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoreModule::class)
class ChatbotScreenTest {

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
                    startDestination = Screen.Chatbot.route
                )
            }
        }
    }

    @Test
    fun checkChatbotSendsInitialMessage() {
        composeRule
            .onNodeWithText("Hi! How can I help you?")
            .assertIsDisplayed()
    }

    @Test
    fun checkTextInputIsDisplayed() {
        composeRule
            .onNodeWithText(context.getString(R.string.send))
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.type_a_message))
            .assertIsDisplayed()
    }

    @Test
    fun checkTextInputIsEditable() {
        composeRule
            .onNodeWithText(context.getString(R.string.type_a_message))
            .performTextInput("Hello")
        composeRule
            .onNodeWithText("Hello")
            .assertIsDisplayed()
    }
}