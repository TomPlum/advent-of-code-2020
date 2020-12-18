package io.github.tomplum.aoc.aircraft.homework

import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

class ExpressionEngine(private val strategy: OperationOrderStrategy) {
    fun sum(expressions: List<Expression>): Long = expressions.map { it.solve(strategy).value }.sum()
}