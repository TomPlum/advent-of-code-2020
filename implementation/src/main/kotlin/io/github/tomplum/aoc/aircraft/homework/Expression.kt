package io.github.tomplum.aoc.aircraft.homework

data class Expression(val tokens: List<Token>): Term {
    override fun solve(): Int = 0

    override fun getLength(): Int = tokens.sumBy { it.getLength() }

    override fun toString(): String = tokens.joinToString(" ")
}