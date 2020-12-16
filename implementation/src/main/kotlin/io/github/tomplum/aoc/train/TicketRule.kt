package io.github.tomplum.aoc.train

data class TicketRule(val name: String, val lowerBound: IntRange, val upperBound: IntRange) {
    fun apply(value: Int): Boolean = value in lowerBound || value in upperBound
}