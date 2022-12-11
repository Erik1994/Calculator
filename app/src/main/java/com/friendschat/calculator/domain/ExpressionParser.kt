package com.friendschat.calculator.domain

import com.friendschat.calculator.domain.Operation.Companion.getOperationFromSymbol
import com.friendschat.calculator.domain.Operation.Companion.getOperationSymbols
import com.friendschat.calculator.util.ExpressionPart
import com.friendschat.calculator.util.ParenthesesType

class ExpressionParser(
    private val calculation: String
) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculation.length) {
            val curChar = calculation[i]
            when {
                curChar in getOperationSymbols() -> {
                    result.add(
                        ExpressionPart.Op(getOperationFromSymbol(curChar))
                    )
                }
                curChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }
                curChar in "()" -> {
                    parseParentheses(curChar, result)
                }
            }
            i++
        }
        return result
    }

    private fun parseNumber(startingIndex: Int, result: MutableList<ExpressionPart>): Int {
        var i = startingIndex
        val numberAsString = buildString {
            while (i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }
        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }

    private fun parseParentheses(c: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parentheses(
                type = when (c) {
                    '(' -> ParenthesesType.Opening
                    ')' -> ParenthesesType.Closing
                    else -> throw IllegalArgumentException("Invalid argument type")
                }
            )
        )
    }
}