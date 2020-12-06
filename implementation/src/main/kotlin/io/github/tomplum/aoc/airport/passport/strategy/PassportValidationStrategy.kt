package io.github.tomplum.aoc.airport.passport.strategy

import io.github.tomplum.aoc.airport.passport.Passport
import io.github.tomplum.aoc.airport.passport.PassportField

/**
 * A strategy for validating a [Passport]
 */
interface PassportValidationStrategy {
    fun isValid(info: Map<PassportField, String>): Boolean
}