package com.friendschat.calculator.domain

import com.friendschat.calculator.util.ExpressionPart
import com.friendschat.calculator.util.ParenthesesType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `simple expression is properly parsed`() {

        // 1. Given
        parser = ExpressionParser("3+5-3x4/3")

        // 2. Do something with what's given
        val actual = parser.parse()

        // 3. Assert expected == Actual
        val excpected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DEVIDE),
            ExpressionPart.Number(3.0)
        )

        assertThat(excpected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parenthesis is properly parced` () {
        parser = ExpressionParser("4x(7+3)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(7.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(3.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing)
        )

        assertThat(expected).isEqualTo(actual)
    }
}