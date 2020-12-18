package io.github.tomplum.aoc.aircraft.homework

class ExpressionEngine(private val expressions: List<Expression>) {
    fun solve(): Int = expressions.sumBy { it.solve().value }
}