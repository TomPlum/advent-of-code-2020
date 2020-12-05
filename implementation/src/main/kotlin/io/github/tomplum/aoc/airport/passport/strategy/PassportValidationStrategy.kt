package io.github.tomplum.aoc.airport.passport.strategy

import io.github.tomplum.aoc.airport.passport.PassportField

interface PassportValidationStrategy {
    fun isValid(info: Map<PassportField, String>): Boolean
}