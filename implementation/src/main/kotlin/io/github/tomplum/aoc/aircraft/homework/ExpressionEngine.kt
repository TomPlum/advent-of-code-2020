package io.github.tomplum.aoc.aircraft.homework

import io.github.tomplum.aoc.aircraft.homework.types.Expression
import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

/**
 * Resolves expressions according to the given [strategy].
 * @param strategy The order of operations by which the engine will abide by.
 */
class ExpressionEngine(private val strategy: OperationOrderStrategy) {
    /**
     * Solves all of the given [expressions].
     * @param expressions The expressions to solve.
     * @return The sum of all the answers.
     */
    fun sum(expressions: List<Expression>): Long = expressions.map { it.solve(strategy).value }.sum()
}