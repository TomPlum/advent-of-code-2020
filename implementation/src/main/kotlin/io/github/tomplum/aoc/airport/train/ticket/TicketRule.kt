package io.github.tomplum.aoc.airport.train.ticket

/**
 * A rule governing the validating of a single field in a [Ticket].
 * @see TrainServiceDocument
 *
 * @param name The name of the field.
 * @param lowerBound The first range of numbers the given field value should fall between.
 * @param upperBound The second range of numbers the given field value should fall between.
 */
data class TicketRule(val name: String, private val lowerBound: IntRange, private val upperBound: IntRange) {
    /**
     * Applies the rule to the given [value].
     * @return true if the value is within either of the ranges, else false.
     */
    fun apply(value: Int): Boolean = value in lowerBound || value in upperBound
}