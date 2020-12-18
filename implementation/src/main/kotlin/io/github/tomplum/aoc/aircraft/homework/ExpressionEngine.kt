package io.github.tomplum.aoc.aircraft.homework

class ExpressionEngine(private val expressions: List<Expression>) {
    fun solve(): Long = expressions.map { it.solve().value.toLong() }.sum()
}