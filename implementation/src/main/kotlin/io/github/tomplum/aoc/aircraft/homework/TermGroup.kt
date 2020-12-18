package io.github.tomplum.aoc.aircraft.homework

data class TermGroup(val terms: List<Term>): Token {
    override fun getLength(): Int = terms.sumBy { it.getLength() }

    override fun toString(): String = "(${terms.joinToString(" ")})"
}