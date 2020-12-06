package io.github.tomplum.aoc.airport.passport

import io.github.tomplum.aoc.airport.passport.strategy.PassportValidationStrategy

/**
 * An automatic scanner at passport control that checks the validity of the given passports.
 * @param strategy The strategy used to validate the passports.
 */
class PassportScanner(private val strategy: PassportValidationStrategy) {
    /**
     * Checks if the given [passports] are valid.
     * @return The number of valid passports.
     */
    fun scan(passports: List<Passport>): Int = passports.filter { it.isValid(strategy) }.count()
}