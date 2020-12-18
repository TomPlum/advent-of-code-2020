package io.github.tomplum.aoc.aircraft.homework.types

import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

/**
 * A single linear mathematical expression.
 * This type of expression does not conform the the standard order of operations.
 * @param tokens The terms, operators and other tokens that form the expression.
 */
data class Expression(val tokens: List<Token>): Token {

    /**
     * Solves the expression as per the order of operations defined in the given [strategy].
     * @param strategy The order of operations the expression should abide by.
     * @return The result as a single [Number].
     */
    fun solve(strategy: OperationOrderStrategy): Number = strategy.resolve(tokens)

    /**
     * Calculates the total length of all the [tokens] in the expression.
     * @return The expression length.
     */
    override fun getLength(): Int = tokens.sumBy { it.getLength() }

    override fun toString(): String = tokens.joinToString(" ") {
        if (it is Expression) "($it)" else it.toString()
    }
}