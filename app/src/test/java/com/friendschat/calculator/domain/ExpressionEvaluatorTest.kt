package com.friendschat.calculator.domain

import com.friendschat.calculator.util.ExpressionPart
import com.friendschat.calculator.util.ParenthesesType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionEvaluatorTest {
    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0)
            )
        )

        val actual = evaluator.evaluate()
        val expevted = 4

        assertThat(actual).isEqualTo(expevted)
    }

    @Test
    fun `Simple equation with parentheses properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(4.0),
            )
        )

        val actual = evaluator.evaluate()
        val expevted = 6.5

        assertThat(actual).isEqualTo(expevted)
    }
}