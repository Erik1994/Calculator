package com.friendschat.calculator.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.friendschat.calculator.MainActivity
import com.friendschat.calculator.presentation.viewmodel.CalculatorViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {

    @get:Rule
    val composRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun enterExpression_correctResultDisplayed() {
        composRule.onNodeWithText("3").performClick()
        composRule.onNodeWithText("x").performClick()
        composRule.onNodeWithText("5").performClick()
        composRule.onNodeWithText("+").performClick()
        composRule.onNodeWithText("21").performClick()
        composRule.onNodeWithText("-").performClick()
        composRule.onNodeWithText("15").performClick()
        composRule.onNodeWithText("=").performClick()

        composRule.onNodeWithText("21.0").assertIsDisplayed()
    }
}