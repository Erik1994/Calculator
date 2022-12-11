package com.friendschat.calculator.domain

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DEVIDE('/'),
    PERCENT('%');

    companion object {
        fun getOperationSymbols() = values().map { it.symbol }.joinToString("")
        fun getOperationFromSymbol(symbol: Char) = values().find { it.symbol == symbol }
            ?: throw IllegalArgumentException("Illegal argument")
    }
}
