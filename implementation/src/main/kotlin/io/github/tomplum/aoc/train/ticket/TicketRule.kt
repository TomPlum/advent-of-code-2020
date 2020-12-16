package io.github.tomplum.aoc.train.ticket

data class TicketRule(val name: String, private val lowerBound: IntRange, private val upperBound: IntRange) {
    fun apply(value: Int): Boolean = value in lowerBound || value in upperBound
}