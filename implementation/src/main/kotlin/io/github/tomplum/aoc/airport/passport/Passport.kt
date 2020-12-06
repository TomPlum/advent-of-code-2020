package io.github.tomplum.aoc.airport.passport

import io.github.tomplum.aoc.airport.passport.strategy.PassportValidationStrategy

/**
 * A single passport belonging to a passenger.
 * @param info A map of the fields listed in the passport with their respective values.
 */
data class Passport(private val info: Map<PassportField, String>)  {
    /**
     * Checks if the passport is valid according to the given [strategy].
     * @return true if valid, else false.
     */
    fun isValid(strategy: PassportValidationStrategy): Boolean = strategy.isValid(info)
}